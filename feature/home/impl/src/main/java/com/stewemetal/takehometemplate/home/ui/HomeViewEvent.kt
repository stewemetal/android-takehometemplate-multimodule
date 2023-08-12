package com.stewemetal.takehometemplate.home.ui

import com.stewemetal.takehometemplate.shell.domain.model.ItemId

sealed interface HomeViewEvent {
    data class ItemClicked(
        val itemId: ItemId,
    ) : HomeViewEvent

    data object BackClicked : HomeViewEvent

    data object NavigationEventConsumed : HomeViewEvent
}
