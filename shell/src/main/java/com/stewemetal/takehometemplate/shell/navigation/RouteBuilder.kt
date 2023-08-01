package com.stewemetal.takehometemplate.shell.navigation

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.stewemetal.takehometemplate.shell.R
import com.stewemetal.takehometemplate.shell.navigation.model.BottomSheetRoute
import com.stewemetal.takehometemplate.shell.navigation.model.DialogRoute
import com.stewemetal.takehometemplate.shell.navigation.model.EnterExitTransitions
import com.stewemetal.takehometemplate.shell.navigation.model.ForwardBackTransitions
import com.stewemetal.takehometemplate.shell.navigation.model.ScreenRoute

/**
 * Build a Route to navigate to a Fragment
 *
 * @param A The Activity that will host the Fragment [F]
 * @param F The Fragment to navigate to
 * @param extras A map to provide extra arguments for the target Fragment. By default, the map is `null`.
 * @param activityTransition The enter and exit transitions for the Activity. The project's default transitions
 * are provided by default with [getDefaultActivityTransition]
 * @param fragmentTransition The enter and exit transitions for the Fragment. The project's default transitions
 * are provided by default with [getDefaultFragmentTransition]
 */
inline fun <reified A : Activity, reified F : Fragment> fragmentRoute(
    extras: Map<String, Any?>? = null,
    activityTransition: ForwardBackTransitions? = getDefaultActivityTransition(),
    fragmentTransition: ForwardBackTransitions? = getDefaultFragmentTransition(),
) = ScreenRoute(
    activityType = A::class.java,
    fragmentType = F::class.java,
    extras = extras?.toBundle(),
    activityTransition = activityTransition,
    fragmentTransition = fragmentTransition,
)

/**
 * Build a Route to navigate to an Activity
 *
 * @param A The Activity that will be displayed.
 * @param extras A map to provide extra arguments for the target Activity. By default, the map is `null`.
 * @param activityTransition The enter and exit transitions for the Activity. The project's default transitions
 * are provided by default with [getDefaultActivityTransition]
 */
inline fun <reified A : Activity> activityRoute(
    extras: Map<String, Any?>? = null,
    activityTransition: ForwardBackTransitions? = getDefaultActivityTransition(),
) = ScreenRoute(
    activityType = A::class.java,
    fragmentType = null,
    extras = extras?.toBundle(),
    activityTransition = activityTransition,
)

/**
 * Build a Route to navigate to a Bottom sheet
 *
 * @param BS The Fragment that will be used in the BottomSheet
 * @param extras A map to provide extra arguments for the target Fragment. By default, the map is `null`.
 */
inline fun <reified BS : BottomSheetDialogFragment> bottomSheetRoute(
    extras: Map<String, Any?>? = null,
) =
    BottomSheetRoute(
        sheetFragmentType = BS::class.java,
        sheetContentType = null,
        extras = extras?.toBundle(),
        contentTransition = null,
    )

/**
 * Build a Route to navigate to a Fragment inside a BottomSheet.
 * This is used when the BottomSheet has navigation with multiple Fragments
 *
 * @param BS The Fragment that will host the navigation in the Bottom Sheet
 * @param F The Fragment to navigate to
 * @param extras A map to provide extra arguments for the target Fragment. By default, the map is `null`.
 * @param contentTransition The enter and exit transitions for the hosted Fragment [F]. The project's default
 * transitions are provided by default with [getDefaultFragmentTransition]
 */
inline fun <reified BS : BottomSheetDialogFragment, reified F : Fragment> bottomSheetSubRoute(
    extras: Map<String, Any?>? = null,
    contentTransition: ForwardBackTransitions? = getDefaultFragmentTransition(),
) = BottomSheetRoute(
    sheetFragmentType = BS::class.java,
    sheetContentType = F::class.java,
    extras = extras?.toBundle(),
    contentTransition = contentTransition,
)

/**
 * Build a Route to navigate to a Dialog
 *
 * @property title The title of the dialog. Optional.
 * @property message The message showed in the dialog. Required.
 * @property positiveButtonText The text of the positive button. Required.
 * @property positiveButtonClick The [DialogInterface.OnClickListener ]for the positive button. Required.
 * @property negativeButtonText The text of the negative button. Optional.
 * @property negativeButtonClick The [DialogInterface.OnClickListener] for the negative button. Optional.
 * @property neutralButtonText The text of the neutral button. Optional.
 * @property neutralButtonClick The [DialogInterface.OnClickListener] for the neutral button. Optional.
 * @property themeResId The resource ID of the dialog's theme. Optional with a default value.
 */
fun dialogRoute(
    title: String? = null,
    message: String,
    positiveButtonText: String,
    positiveButtonClick: DialogInterface.OnClickListener,
    negativeButtonText: String? = null,
    negativeButtonClick: DialogInterface.OnClickListener? = null,
    neutralButtonText: String? = null,
    neutralButtonClick: DialogInterface.OnClickListener? = null,
    themeResId: Int? = androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog,
): DialogRoute =
    DialogRoute(
        title = title,
        message = message,
        positiveButtonText = positiveButtonText,
        positiveButtonClick = positiveButtonClick,
        negativeButtonText = negativeButtonText,
        negativeButtonClick = negativeButtonClick,
        neutralButtonText = neutralButtonText,
        neutralButtonClick = neutralButtonClick,
        themeResId = themeResId,
    )

@Suppress("SpreadOperator")
fun Map<String, Any?>.toBundle(): Bundle {
    return bundleOf(*map { Pair(it.key, it.value) }.toTypedArray())
}

fun getDefaultActivityTransition() = ForwardBackTransitions(
    startAnim = EnterExitTransitions(
        R.anim.proceeding_enter,
        R.anim.proceeding_exit,
    ),
    backAnim = EnterExitTransitions(
        R.anim.proceeding_pop_enter,
        R.anim.proceeding_pop_exit,
    ),
)

fun getDefaultFragmentTransition() = ForwardBackTransitions(
    startAnim = EnterExitTransitions(
        R.anim.proceeding_enter,
        R.anim.proceeding_exit,
    ),
    backAnim = EnterExitTransitions(
        R.anim.proceeding_pop_enter,
        R.anim.proceeding_pop_exit,
    ),
)
