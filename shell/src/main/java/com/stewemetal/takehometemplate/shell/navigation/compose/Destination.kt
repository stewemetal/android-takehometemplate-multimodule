package com.stewemetal.takehometemplate.shell.navigation.compose

sealed class Destination(val route: String) {
    data object LoginDestination : Destination("home_screen")
    data object ListDestination : Destination("list_screen")
    data object DetailsDestination : Destination("details_screen")
}
