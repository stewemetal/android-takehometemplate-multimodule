package com.stewemetal.takehometemplate.itemlist.ui

import com.stewemetal.takehometemplate.shell.domain.model.Item
import com.stewemetal.takehometemplate.shell.domain.model.ItemId

internal data class ItemListState(
    val isLoading: Boolean = true,
    val items: List<Item> = emptyList(),
    val navigationEvent: HomeNavigationEvent? = null,
)

internal sealed interface HomeNavigationEvent {
    data class NavigateToItemDetails(
        val itemId: ItemId,
    ) : HomeNavigationEvent

    data object NavigateBack : HomeNavigationEvent
}
