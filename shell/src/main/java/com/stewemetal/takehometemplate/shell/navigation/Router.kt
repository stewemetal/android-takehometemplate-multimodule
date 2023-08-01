package com.stewemetal.takehometemplate.shell.navigation

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import com.stewemetal.takehometemplate.shell.navigation.model.Destination
import com.stewemetal.takehometemplate.shell.navigation.model.ExternalApp
import com.stewemetal.takehometemplate.shell.navigation.model.LaunchScreenFlags

interface Router {

    /**
     * Perform navigation to a specific [Destination].
     *
     * When [finishCurrentActivity] is enabled, the current activity will be finished after navigating to the new
     * destination.
     *
     * The enum [flags] can be used to specify an Intent flag, mapped by [LaunchScreenFlags].
     */
    fun navigateTo(
        destination: Destination,
        finishCurrentActivity: Boolean = false,
        flags: LaunchScreenFlags? = null,
    )

    /**
     * Navigate to multiple Destinations.
     * This method can be used to navigate with a deeplink and build a backstack.
     */
    fun navigateTo(vararg destinations: Destination)

    /**
     *  This method can be called in every scenario.
     *
     *  - If a bottom sheet is currently displayed, it will be closed unless it has multiple screens.
     *    In such a case, the top one will be popped.
     *  - If an Activity has multiple Fragments on its backstack, the top one will be popped.
     *  - If only one Fragment is present, the currently visible Activity will be finished.
     *  - If an Activity has no Fragments at all, it will be finished automatically.
     */
    fun navigateBack()

    /**
     * Same behavior as [navigateBack], but the Fragment back stack will be cleared until the first Fragment
     * in the stack.
     *
     * For example, in the following case: "Activity, FragmentA, FragmentB, FragmentC",
     * the result will be "Activity, FragmentA".
     */
    fun navigateBackAndClearStack()

    /**
     * Close the currently displayed BottomSheet, if present.
     */
    fun closeBottomSheet()

    /**
     * Finish the currently displayed Activity.
     */
    fun finishActivity()

    /**
     * Open an external app described by [ExternalApp].
     */
    fun openExternalApp(externalApp: ExternalApp)

    /**
     * Connect the current Activity to the Router.
     *
     * This method should only be called by the Base Activity classes.
     */
    @InternalNavigationApi
    fun bindActivityOnCreate(activity: AppCompatActivity, @IdRes fragmentHostId: Int?)

    /**
     * Connect the current Activity to the Router.
     *
     * This method should only be called by the Base Activity classes.
     */
    @InternalNavigationApi
    fun bindActivityOnResume(activity: AppCompatActivity, @IdRes fragmentHostId: Int?)
}
