package com.stewemetal.takehometemplate.shell.navigation.model

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

sealed interface Route

data class ScreenRoute(
    val activityType: Class<out Activity>,
    val fragmentType: Class<out Fragment>? = null,
    val extras: Bundle? = null,
    val activityTransition: ForwardBackTransitions? = null,
    val fragmentTransition: ForwardBackTransitions? = null,
) : Route

data class BottomSheetRoute(
    val sheetFragmentType: Class<out BottomSheetDialogFragment>,
    val sheetContentType: Class<out Fragment>? = null,
    val extras: Bundle? = null,
    val contentTransition: ForwardBackTransitions? = null,
) : Route

data class DialogRoute(
    val title: String? = null,
    val message: String,
    val positiveButtonText: String,
    val positiveButtonClick: DialogInterface.OnClickListener,
    val negativeButtonText: String? = null,
    val negativeButtonClick: DialogInterface.OnClickListener? = null,
    val neutralButtonText: String? = null,
    val neutralButtonClick: DialogInterface.OnClickListener? = null,
    val themeResId: Int? = null,
) : Route
