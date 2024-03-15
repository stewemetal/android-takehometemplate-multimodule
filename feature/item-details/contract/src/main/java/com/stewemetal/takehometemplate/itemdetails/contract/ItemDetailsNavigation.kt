package com.stewemetal.takehometemplate.itemdetails.contract

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import com.stewemetal.takehometemplate.shell.domain.model.ItemId

const val ItemDetailsRoute = "item_details"
const val ItemDetailsItemIdArg = "itemId"

interface ItemDetailsNavGraphFactory {

    fun buildNavGraph(
        navGraphBuilder: NavGraphBuilder,
        onNavigateBack: () -> Unit,
    )
}

fun NavController.navigateToItemDetails(
    itemId: ItemId,
    navOptionsBuilder: NavOptionsBuilder.() -> Unit = {},
) {
    this.navigate("$ItemDetailsRoute/${itemId.value}", navOptionsBuilder)
}

class ItemDetailsArgs(
    val itemId: String,
) {
    constructor(savedStateHandle: SavedStateHandle) :
        this(checkNotNull(savedStateHandle[ItemDetailsItemIdArg]) as String)
}
