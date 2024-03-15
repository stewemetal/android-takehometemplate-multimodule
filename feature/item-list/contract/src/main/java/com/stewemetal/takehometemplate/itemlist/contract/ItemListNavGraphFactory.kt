package com.stewemetal.takehometemplate.itemlist.contract

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import com.stewemetal.takehometemplate.shell.domain.model.ItemId

const val ItemListRoute = "item-list"

interface ItemListNavGraphFactory {

    fun buildNavGraph(
        navGraphBuilder: NavGraphBuilder,
        onNavigateBack: () -> Unit,
        onNavigateToDetailsScreen: (ItemId) -> Unit,
    )
}

fun NavController.navigateToHome(
    navOptionsBuilder: NavOptionsBuilder.() -> Unit = {},
) {
    this.navigate(ItemListRoute, navOptionsBuilder)
}
