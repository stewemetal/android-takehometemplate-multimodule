package com.stewemetal.takehometemplate.itemlist.ui

import com.stewemetal.takehometemplate.shell.domain.model.ItemId

sealed interface ItemListViewEvent {
    data class ItemClicked(
        val itemId: ItemId,
    ) : ItemListViewEvent

    data object AppBarNavigationClicked : ItemListViewEvent

    data object NavigationEventConsumed : ItemListViewEvent
}
