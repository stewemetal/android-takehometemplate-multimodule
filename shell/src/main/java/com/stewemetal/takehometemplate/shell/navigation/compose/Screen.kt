package com.stewemetal.takehometemplate.shell.navigation.compose

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home_screen")
    data object ListScreen : Screen("list_screen")
    data object DetailsScreen : Screen("details_screen")
}
