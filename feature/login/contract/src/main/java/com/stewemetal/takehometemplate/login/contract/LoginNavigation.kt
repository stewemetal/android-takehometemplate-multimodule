package com.stewemetal.takehometemplate.login.contract

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions

const val LoginRoute = "login"

interface LoginNavGraphFactory {
    fun buildNavGraph(
        builder: NavGraphBuilder,
        onNavigateToMainScreen: () -> Unit,
    )
}

fun NavController.navigateToLogin(navOptions: NavOptions? = null) {
    this.navigate(LoginRoute, navOptions)
}
