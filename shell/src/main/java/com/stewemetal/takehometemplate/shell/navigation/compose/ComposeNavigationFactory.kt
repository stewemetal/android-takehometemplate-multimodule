package com.stewemetal.takehometemplate.shell.navigation.compose

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

interface ComposeNavigationFactory {
    fun create(navGraphBuilder: NavGraphBuilder, navController: NavController)
}
