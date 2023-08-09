package com.stewemetal.takehometemplate.itemdetails.contract

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import com.stewemetal.takehometemplate.shell.domain.ItemId

const val ItemDetailsRoute = "item_details"
const val ItemIdArg = "itemId"

interface ItemDetailsNavGraphFactory {

    fun buildNavGraph(
        builder: NavGraphBuilder,
        onNavigateBack: () -> Unit,
    )
}

fun NavController.navigateToItemDetails(
    itemId: ItemId,
    navOptionsBuilder: NavOptionsBuilder.() -> Unit = {},
) {
    this.navigate("$ItemDetailsRoute/${itemId.value}", navOptionsBuilder)
}
