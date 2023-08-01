package com.stewemetal.takehometemplate.shell.navigation.internal

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.stewemetal.takehometemplate.shell.navigation.model.DialogRoute
import com.stewemetal.takehometemplate.shell.navigation.model.EnterExitTransitions
import com.stewemetal.takehometemplate.shell.navigation.model.ForwardBackTransitions
import com.stewemetal.takehometemplate.shell.navigation.model.LaunchScreenFlags

@Suppress("TooManyFunctions")
internal class NavigatorImpl : Navigator {
    override fun startActivity(
        context: Context,
        intent: Intent,
        transition: EnterExitTransitions?,
    ) {
        context.startActivity(intent)
        if (context is AppCompatActivity && transition != null) {
            context.overridePendingTransition(transition.enterAnim, transition.exitAnim)
        }
    }

    override fun prepareIntent(
        context: Context,
        clazz: Class<out Activity>,
        extras: Bundle?,
        flags: LaunchScreenFlags?,
    ) = Intent(context, clazz).apply {
        extras?.let {
            putExtras(extras)
        }
        flags?.let {
            when (it) {
                LaunchScreenFlags.NO_DUPLICATE_ON_TOP -> this.flags = FLAG_ACTIVITY_SINGLE_TOP
                LaunchScreenFlags.BACK_ON_LAST_INSTANCE_AND_CLEAR_BACKSTACK -> {
                    this.flags = FLAG_ACTIVITY_CLEAR_TOP
                }
                LaunchScreenFlags.CLEAR_WHOLE_BACKSTACK -> {
                    this.flags = FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK
                }
            }
        }
    }

    override fun createFragment(
        activity: AppCompatActivity,
        clazz: Class<out Fragment>,
        extras: Bundle?,
    ): Fragment {
        val fragment = activity.supportFragmentManager.fragmentFactory.instantiate(
            activity.classLoader,
            clazz.name,
        )
        return fragment.apply {
            arguments = extras
        }
    }

    override fun replaceFragment(
        activity: AppCompatActivity,
        @IdRes fragmentHostId: Int,
        fragment: Fragment,
        tag: String,
        addToBackStack: Boolean,
        backStackEntryName: String?,
        transition: ForwardBackTransitions?,
    ) {
        activity.supportFragmentManager.beginTransaction().apply {
            if (transition != null) {
                setCustomAnimations(
                    transition.startAnim.enterAnim,
                    transition.startAnim.exitAnim,
                    transition.backAnim.enterAnim,
                    transition.backAnim.exitAnim,
                )
            }
            replace(fragmentHostId, fragment, tag)

            if (addToBackStack) {
                addToBackStack(backStackEntryName)
            }
        }.commitAllowingStateLoss()
    }

    override fun createBottomSheet(
        activity: AppCompatActivity,
        clazz: Class<out BottomSheetDialogFragment>,
        extras: Bundle?,
    ): BottomSheetDialogFragment {
        val fragment = activity.supportFragmentManager.fragmentFactory.instantiate(
            activity.classLoader,
            clazz.name,
        ) as BottomSheetDialogFragment
        return fragment.apply {
            arguments = extras
        }
    }

    override fun showBottomSheet(
        activity: AppCompatActivity,
        sheet: BottomSheetDialogFragment,
        tag: String,
    ) = sheet.show(activity.supportFragmentManager, tag)

    override fun findBottomSheet(
        activity: AppCompatActivity,
        tag: String,
    ) =
        activity.supportFragmentManager.findFragmentByTag(tag) as? BottomSheetDialogFragment

    override fun createFragmentInBottomSheet(
        bottomSheetDialogFragment: BottomSheetDialogFragment,
        clazz: Class<out Fragment>,
        extras: Bundle?,
    ): Fragment = createFragment(
        activity = bottomSheetDialogFragment.requireActivity() as AppCompatActivity,
        clazz = clazz,
        extras = extras,
    )

    override fun replaceFragmentOnBottomSheet(
        bottomSheetDialogFragment: BottomSheetDialogFragment,
        @IdRes fragmentHostId: Int,
        fragment: Fragment,
        tag: String,
        addToBackStack: Boolean,
        backStackEntryName: String?,
        transition: ForwardBackTransitions?,
    ) {
        bottomSheetDialogFragment.childFragmentManager.beginTransaction().apply {
            if (transition != null) {
                setCustomAnimations(
                    transition.startAnim.enterAnim,
                    transition.startAnim.exitAnim,
                    transition.backAnim.enterAnim,
                    transition.backAnim.exitAnim,
                )
            }
            replace(fragmentHostId, fragment, tag)
            if (addToBackStack) {
                addToBackStack(backStackEntryName)
            }
        }.commitAllowingStateLoss()
    }

    override fun getFragmentBackStackCount(activity: AppCompatActivity): Int =
        activity.supportFragmentManager.backStackEntryCount

    override fun getBottomSheetBackStackCount(bottomSheet: BottomSheetDialogFragment): Int =
        bottomSheet.childFragmentManager.backStackEntryCount

    override fun popBackStack(activity: AppCompatActivity) {
        activity.supportFragmentManager.popBackStackImmediate()
    }

    override fun popAndClearBackStack(activity: AppCompatActivity) {
        activity.supportFragmentManager.popBackStackImmediate(null, POP_BACK_STACK_INCLUSIVE)
    }

    override fun popBackStackOnBottomSheet(bottomSheet: BottomSheetDialogFragment) {
        bottomSheet.childFragmentManager.popBackStackImmediate()
    }

    override fun popAndClearBackStackOnBottomSheet(bottomSheet: BottomSheetDialogFragment) {
        bottomSheet.childFragmentManager.popBackStackImmediate(null, POP_BACK_STACK_INCLUSIVE)
    }

    override fun finishActivity(
        activity: AppCompatActivity,
        transition: EnterExitTransitions?,
    ) {
        activity.apply {
            finish()
            if (transition != null) {
                overridePendingTransition(transition.enterAnim, transition.exitAnim)
            }
        }
    }

    override fun showDialog(
        activity: AppCompatActivity,
        route: DialogRoute,
    ) {
        val dialogBuilder = getDialogBuilder(activity, route.themeResId)
            .setMessage(route.message)
            .setPositiveButton(route.positiveButtonText, route.positiveButtonClick)

        val title = route.title
        if (title != null) {
            dialogBuilder.setTitle(title)
        }

        val negativeButtonText = route.negativeButtonText
        val negativeButtonClickListener = route.negativeButtonClick
        if (negativeButtonText != null && negativeButtonClickListener != null) {
            dialogBuilder.setNegativeButton(negativeButtonText, negativeButtonClickListener)
        }

        val neutralButtonText = route.neutralButtonText
        val neutralButtonClickListener = route.neutralButtonClick
        if (neutralButtonText != null && neutralButtonClickListener != null) {
            dialogBuilder.setNeutralButton(neutralButtonText, neutralButtonClickListener)
        }

        dialogBuilder.show()
    }

    override fun getFragmentTree(activity: AppCompatActivity): List<Fragment> =
        activity.getFragmentTree()

    private fun getDialogBuilder(
        activity: AppCompatActivity,
        themeResId: Int?,
    ): AlertDialog.Builder =
        themeResId?.let { resId ->
            AlertDialog.Builder(activity, resId)
        } ?: AlertDialog.Builder(activity)
}

private fun AppCompatActivity.getFragmentTree(): List<Fragment> =
    supportFragmentManager.fragments
        .flatMap { fragment ->
            fragment.getChildFragmentTreeBottomToTop()
        }

private fun Fragment.getChildFragmentTreeBottomToTop(): List<Fragment> {
    val children = childFragmentManager.fragments
    return children.flatMap { it.getChildFragmentTreeBottomToTop() } + this
}
