@file:OptIn(InternalNavigationApi::class)

package com.stewemetal.takehometemplate.shell.navigation.internal

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.stewemetal.takehometemplate.shell.navigation.BackPressAware
import com.stewemetal.takehometemplate.shell.navigation.FragmentNavHostIdProvider
import com.stewemetal.takehometemplate.shell.navigation.GlobalRouteResolver
import com.stewemetal.takehometemplate.shell.navigation.InternalNavigationApi
import com.stewemetal.takehometemplate.shell.navigation.Router
import com.stewemetal.takehometemplate.shell.navigation.model.BottomSheetRoute
import com.stewemetal.takehometemplate.shell.navigation.model.Destination
import com.stewemetal.takehometemplate.shell.navigation.model.DialogRoute
import com.stewemetal.takehometemplate.shell.navigation.model.EnterExitTransitions
import com.stewemetal.takehometemplate.shell.navigation.model.ExternalApp
import com.stewemetal.takehometemplate.shell.navigation.model.ForwardBackTransitions
import com.stewemetal.takehometemplate.shell.navigation.model.LaunchScreenFlags
import com.stewemetal.takehometemplate.shell.navigation.model.ScreenRoute

internal class RouterImpl(
    private val navigator: Navigator,
    private var globalRouterResolver: GlobalRouteResolver,
) : Router {

    private var currentActivityWrapper: ActivityWrapper? = null
    private var destinationFragmentWrapper: FragmentWrapper? = null
    private var nextBackTransition: EnterExitTransitions? = null

    private var currentDestination: Destination? = null

    @Synchronized
    override fun navigateTo(
        destination: Destination,
        finishCurrentActivity: Boolean,
        flags: LaunchScreenFlags?,
    ) {
        if (destination != currentDestination) {
            currentDestination = destination
            when (val route = globalRouterResolver.resolveRoute(destination)) {
                is ScreenRoute -> navigateToScreen(route, finishCurrentActivity, flags)
                is BottomSheetRoute -> showBottomSheet(route)
                is DialogRoute -> showDialog(route)
            }
            currentDestination = null
        }
    }

    override fun navigateTo(vararg destinations: Destination) {
        for (dest in destinations) {
            navigateTo(dest)
        }
    }

    override fun navigateBack() {
        val activityWrapper = getActivityWrapper()
        val activity = activityWrapper.activity
        val currentBottomSheet = getCurrentBottomSheet()
        if (currentBottomSheet != null) {
            if (navigator.getBottomSheetBackStackCount(currentBottomSheet) > 1) {
                navigator.popBackStackOnBottomSheet(currentBottomSheet)
            } else {
                currentBottomSheet.dismiss()
            }
        } else {
            // Zero because the first fragment is not added to the backstack
            if (navigator.getFragmentBackStackCount(activity) > 0) {
                navigator.popBackStack(activity)
            } else {
                navigator.finishActivity(activity, transition = nextBackTransition)
            }
        }
    }

    override fun navigateBackAndClearStack() {
        val activityWrapper = getActivityWrapper()
        val activity = activityWrapper.activity
        val currentBottomSheet = getCurrentBottomSheet()
        if (currentBottomSheet != null) {
            if (navigator.getBottomSheetBackStackCount(currentBottomSheet) > 1) {
                navigator.popAndClearBackStackOnBottomSheet(currentBottomSheet)
            } else {
                currentBottomSheet.dismiss()
            }
        } else {
            // Zero because the first fragment is not added to the backstack
            if (navigator.getFragmentBackStackCount(activity) > 0) {
                navigator.popAndClearBackStack(activity)
            } else {
                navigator.finishActivity(activity, transition = nextBackTransition)
            }
        }
    }

    override fun closeBottomSheet() {
        getCurrentBottomSheet()?.dismiss()
    }

    override fun finishActivity() {
        val activityWrapper = getActivityWrapper()
        navigator.finishActivity(
            activity = activityWrapper.activity,
            transition = nextBackTransition,
        )
    }

    override fun openExternalApp(externalApp: ExternalApp) {
        val activityWrapper = getActivityWrapper()
        navigator.startActivity(
            context = activityWrapper.activity,
            intent = externalApp.intent,
            transition = null,
        )
    }

    override fun bindActivityOnCreate(activity: AppCompatActivity, @IdRes fragmentHostId: Int?) {
        val activityWrapper = ActivityWrapper(activity, fragmentHostId)
        currentActivityWrapper = activityWrapper

        activity.onBackPressedDispatcher.addCallback(
            activity,
            backPressHandler,
        )
        destinationFragmentWrapper?.let { fragmentWrapper ->
            val destinationFragmentActivity = fragmentWrapper.activityType
            // to prevent issue on deep linking
            if (destinationFragmentActivity == activity.javaClass) {
                navigateToFragment(
                    fragmentType = fragmentWrapper.fragmentType,
                    addToBackstack = false,
                    extras = fragmentWrapper.extras,
                    transition = fragmentWrapper.transition,
                )
                destinationFragmentWrapper = null
            }
        }

        activity.lifecycle.addObserver(
            LifecycleEventObserver { source, event ->
                when (event) {
                    Lifecycle.Event.ON_DESTROY -> {
                        if (source === currentActivityWrapper?.activity) {
                            val message = """
                                    The current activity wrapper is set to null. 
                                    Current activity: ${source.javaClass}
                                    Activity wrapper: $activityWrapper
                            """.trimIndent()
//                            Timber.w(message)
                            nextBackTransition = null
                            currentActivityWrapper = null
                        }
                    }

                    else -> {
                        /* do nothing */
                    }
                }
            },
        )
    }

    override fun bindActivityOnResume(activity: AppCompatActivity, @IdRes fragmentHostId: Int?) {
        val activityWrapper = ActivityWrapper(activity, fragmentHostId)
        currentActivityWrapper = activityWrapper
    }

    private fun navigateToFragment(
        fragmentType: Class<out Fragment>,
        addToBackstack: Boolean,
        extras: Bundle?,
        transition: ForwardBackTransitions?,
    ) {
        val activityWrapper = getActivityWrapper()
        val fragmentHostId = requireNotNull(activityWrapper.fragmentHostId) {
            "The fragmentHostId is null. Please provided it by overriding `getFragmentNavHostId` in your Activity."
        }
        val fragment = navigator.createFragment(activityWrapper.activity, fragmentType, extras)
        navigator.replaceFragment(
            activity = activityWrapper.activity,
            fragmentHostId = fragmentHostId,
            fragment = fragment,
            tag = FRAGMENT_TAG,
            addToBackStack = addToBackstack,
            backStackEntryName = fragment.javaClass.name,
            transition = transition,
        )
    }

    private fun navigateToScreen(
        screenRoute: ScreenRoute,
        finishCurrentActivity: Boolean,
        flags: LaunchScreenFlags?,
    ) {
        val finishActivityTransition = nextBackTransition
        nextBackTransition = screenRoute.activityTransition?.backAnim
        val activityWrapper = getActivityWrapper(routeForLogging = screenRoute)
        val activity = activityWrapper.activity

        // The required Activity is not the one currently displayed. Launch it!
        if (currentActivityWrapper?.activity?.javaClass != screenRoute.activityType) {
            val intent = navigator.prepareIntent(
                context = activity,
                clazz = screenRoute.activityType,
                extras = screenRoute.extras,
                flags = flags,
            )
            destinationFragmentWrapper = screenRoute.fragmentType?.let {
                FragmentWrapper(
                    activityType = screenRoute.activityType,
                    fragmentType = it,
                    extras = screenRoute.extras,
                    transition = screenRoute.fragmentTransition,
                )
            }
            if (finishCurrentActivity) {
                navigator.startActivity(
                    context = activity,
                    intent = intent,
                    transition = screenRoute.activityTransition?.startAnim,
                )

                navigator.finishActivity(
                    activity = activity,
                    transition = finishActivityTransition,
                )
            } else {
                navigator.startActivity(
                    context = activity,
                    intent = intent,
                    transition = screenRoute.activityTransition?.startAnim,
                )
            }
        } else {
            // The required Activity is currently displayed. A Fragment should be displayed on top.
            val fragmentType = requireNotNull(screenRoute.fragmentType) {
                """
                    The fragmentType is null. Have you set it in your Route?
                    Current Route: $screenRoute". Current Activity Wrapper: $currentActivityWrapper
                """.trimIndent()
            }
            navigateToFragment(
                fragmentType,
                addToBackstack = true,
                screenRoute.extras,
                transition = screenRoute.fragmentTransition,
            )
        }
    }

    private fun showBottomSheet(route: BottomSheetRoute) {
        val activityWrapper = getActivityWrapper()
        val currentBottomSheet = getCurrentBottomSheet()

        // A Fragment needs to be shown in the currently shown BottomSheet.
        if (route.sheetContentType != null &&
            currentBottomSheet != null &&
            currentBottomSheet.javaClass == route.sheetFragmentType
        ) {
            navigateToSubBottomSheet(
                hostSheet = currentBottomSheet,
                sheetContentType = route.sheetContentType,
                extras = route.extras,
                transition = route.contentTransition,
            )
        } else {
            // A new BottomSheet needs to be displayed.
            currentBottomSheet?.dismiss()
            val activity = activityWrapper.activity
            val sheet = navigator.createBottomSheet(
                activity = activity,
                clazz = route.sheetFragmentType,
                extras = route.extras,
            )

            navigator.showBottomSheet(activity, sheet, BOTTOM_SHEET_TAG)
            if (route.sheetContentType != null) {
                sheet.lifecycle.addObserver(
                    LifecycleEventObserver { _, event ->
                        when (event) {
                            Lifecycle.Event.ON_CREATE -> {
                                navigateToSubBottomSheet(
                                    hostSheet = sheet,
                                    sheetContentType = route.sheetContentType,
                                    extras = route.extras,
                                    transition = route.contentTransition,
                                )
                            }

                            else -> {
                                /* do nothing */
                            }
                        }
                    },
                )
            }
        }
    }

    private fun navigateToSubBottomSheet(
        hostSheet: BottomSheetDialogFragment,
        sheetContentType: Class<out Fragment>,
        extras: Bundle?,
        transition: ForwardBackTransitions?,
    ) {
        // The BaseComposeBottomSheetFragment will not have a fragment host navigation
        val fragmentHostId = (hostSheet as? FragmentNavHostIdProvider)?.getFragmentNavHostId()
        requireNotNull(fragmentHostId) {
            "A fragment host id is required to navigate inside a bottom sheet"
        }

        val fragment = navigator.createFragmentInBottomSheet(
            bottomSheetDialogFragment = hostSheet,
            clazz = sheetContentType,
            extras = extras,
        )
        navigator.replaceFragmentOnBottomSheet(
            bottomSheetDialogFragment = hostSheet,
            fragmentHostId = fragmentHostId,
            fragment = fragment,
            tag = FRAGMENT_TAG,
            addToBackStack = true,
            backStackEntryName = fragment.javaClass.name,
            transition = transition,
        )
    }

    private fun getCurrentBottomSheet(): BottomSheetDialogFragment? {
        val activityWrapper = getActivityWrapper()
        return navigator.findBottomSheet(activityWrapper.activity, BOTTOM_SHEET_TAG)
    }

    private fun showDialog(route: DialogRoute) {
        val activityWrapper = getActivityWrapper()
        navigator.showDialog(activityWrapper.activity, route)
    }

    /**
     * This object is responsible for implementing the back press event propagation logic to the
     * topmost visible Fragment on the Fragment stack, if that Fragment is [BackPressAware].
     *
     * If there is no [BackPressAware] Fragment on the top of the stack, this Activity will finish.
     */
    private val backPressHandler = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val activityWrapper = getActivityWrapper()
            val activity = activityWrapper.activity
            val didHandle = navigator.getFragmentTree(activity)
                .filter { it.isVisible }
                .mapNotNull { it as? BackPressAware }
                .reversed()
                .any { it.onBackPressed() }

            if (!didHandle) {
                navigateBack()
            }
        }
    }

    private fun getActivityWrapper(routeForLogging: ScreenRoute? = null): ActivityWrapper =
        requireNotNull(currentActivityWrapper) {
            val screenRouteInfo = if (routeForLogging != null) {
                "Current route: $routeForLogging"
            } else {
                ""
            }
            """
                The current Activity is null. Please make sure that your Activity is
                extending BaseActivity, BaseComposeActivity, or BaseViewActivity.
                $screenRouteInfo
            """.trimIndent()
        }

    private companion object {
        const val FRAGMENT_TAG = "current_fragment"
        const val BOTTOM_SHEET_TAG = "current_bottom_sheet"
    }
}
