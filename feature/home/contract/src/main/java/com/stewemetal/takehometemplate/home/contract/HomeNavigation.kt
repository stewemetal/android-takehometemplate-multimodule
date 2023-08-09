package com.stewemetal.takehometemplate.home.contract

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder

const val HomeRoute = "home"

interface HomeNavGraphFactory {

    fun buildNavGraph(
        builder: NavGraphBuilder,
        onNavigateBack: () -> Unit,
        onNavigateToDetailsScreen: (String) -> Unit,
    )
}

fun NavController.navigateToHome(
    navOptionsBuilder: NavOptionsBuilder.() -> Unit = {},
) {
    this.navigate(HomeRoute, navOptionsBuilder)
}
