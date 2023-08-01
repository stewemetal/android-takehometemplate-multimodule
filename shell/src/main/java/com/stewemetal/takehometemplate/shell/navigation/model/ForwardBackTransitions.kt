package com.stewemetal.takehometemplate.shell.navigation.model

import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes

data class ForwardBackTransitions(
    val startAnim: EnterExitTransitions,
    val backAnim: EnterExitTransitions,
)

data class EnterExitTransitions(
    @AnimatorRes @AnimRes val enterAnim: Int,
    @AnimatorRes @AnimRes val exitAnim: Int,
)
