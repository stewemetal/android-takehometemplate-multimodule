package com.stewemetal.takehometemplate.shell.navigation.internal

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.stewemetal.takehometemplate.shell.navigation.model.DialogRoute
import com.stewemetal.takehometemplate.shell.navigation.model.EnterExitTransitions
import com.stewemetal.takehometemplate.shell.navigation.model.ForwardBackTransitions
import com.stewemetal.takehometemplate.shell.navigation.model.LaunchScreenFlags

@Suppress("TooManyFunctions")
interface Navigator {
    fun startActivity(
        context: Context,
        intent: Intent,
        transition: EnterExitTransitions?,
    )

    fun prepareIntent(
        context: Context,
        clazz: Class<out Activity>,
        extras: Bundle?,
        flags: LaunchScreenFlags?,
    ): Intent

    fun createFragment(
        activity: AppCompatActivity,
        clazz: Class<out Fragment>,
        extras: Bundle?,
    ): Fragment

    fun replaceFragment(
        activity: AppCompatActivity,
        @IdRes fragmentHostId: Int,
        fragment: Fragment,
        tag: String,
        addToBackStack: Boolean,
        backStackEntryName: String?,
        transition: ForwardBackTransitions?,
    )

    fun createBottomSheet(
        activity: AppCompatActivity,
        clazz: Class<out BottomSheetDialogFragment>,
        extras: Bundle?,
    ): BottomSheetDialogFragment

    fun showBottomSheet(
        activity: AppCompatActivity,
        sheet: BottomSheetDialogFragment,
        tag: String,
    )

    fun findBottomSheet(
        activity: AppCompatActivity,
        tag: String,
    ): BottomSheetDialogFragment?

    fun createFragmentInBottomSheet(
        bottomSheetDialogFragment: BottomSheetDialogFragment,
        clazz: Class<out Fragment>,
        extras: Bundle?,
    ): Fragment

    fun replaceFragmentOnBottomSheet(
        bottomSheetDialogFragment: BottomSheetDialogFragment,
        @IdRes fragmentHostId: Int,
        fragment: Fragment,
        tag: String,
        addToBackStack: Boolean,
        backStackEntryName: String?,
        transition: ForwardBackTransitions?,
    )

    fun getFragmentBackStackCount(activity: AppCompatActivity): Int

    fun getBottomSheetBackStackCount(bottomSheet: BottomSheetDialogFragment): Int

    fun popBackStack(activity: AppCompatActivity)

    fun popAndClearBackStack(activity: AppCompatActivity)

    fun popBackStackOnBottomSheet(bottomSheet: BottomSheetDialogFragment)

    fun popAndClearBackStackOnBottomSheet(bottomSheet: BottomSheetDialogFragment)

    fun finishActivity(
        activity: AppCompatActivity,
        transition: EnterExitTransitions?,
    )

    fun showDialog(activity: AppCompatActivity, route: DialogRoute)

    fun getFragmentTree(activity: AppCompatActivity): List<Fragment>
}
