package com.stewemetal.takehometemplate.login.contract

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder

const val LoginRoute = "login"

interface LoginNavGraphFactory {
    fun buildNavGraph(
        navGraphBuilder: NavGraphBuilder,
        onNavigateToHomeScreen: () -> Unit,
    )
}

fun NavController.navigateToLogin(
    navOptionsBuilder: NavOptionsBuilder.() -> Unit = { },
) {
    this.navigate(LoginRoute, navOptionsBuilder)
}
