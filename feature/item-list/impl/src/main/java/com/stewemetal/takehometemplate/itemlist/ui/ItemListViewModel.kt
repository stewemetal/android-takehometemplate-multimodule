package com.stewemetal.takehometemplate.itemlist.ui

import androidx.lifecycle.viewModelScope
import com.stewemetal.takehometemplate.itemlist.ui.HomeNavigationEvent.NavigateBack
import com.stewemetal.takehometemplate.itemlist.ui.HomeNavigationEvent.NavigateToItemDetails
import com.stewemetal.takehometemplate.itemlist.ui.ItemListViewEvent.AppBarNavigationClicked
import com.stewemetal.takehometemplate.itemlist.ui.ItemListViewEvent.ItemClicked
import com.stewemetal.takehometemplate.itemlist.ui.ItemListViewEvent.NavigationEventConsumed
import com.stewemetal.takehometemplate.shell.architecture.BaseViewModel
import com.stewemetal.takehometemplate.shell.domain.usecase.GetItemsUseCase
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import timber.log.Timber

@KoinViewModel
internal class ItemListViewModel(
    private val getItemsUseCase: GetItemsUseCase,
) : BaseViewModel<ItemListViewEvent, ItemListState>(
    ItemListState()
) {

    init {
        viewModelScope.launch {
            val items = getItemsUseCase.getItems()
            emitNewState {
                ItemListState(
                    isLoading = false,
                    items = items,
                )
            }
        }
    }

    override fun onViewEvent(event: ItemListViewEvent) {
        Timber.e(">>> HomeVM onViewEvent $event")
        when (event) {
            is ItemClicked -> {
                emitNewState {
                    copy(navigationEvent = NavigateToItemDetails(event.itemId))
                }
            }

            AppBarNavigationClicked -> {
                emitNewState {
                    copy(navigationEvent = NavigateBack)
                }
            }

            NavigationEventConsumed -> {
                emitNewState {
                    copy(navigationEvent = null)
                }
            }
        }
    }

    fun onNavigationEventConsumed() {
        triggerViewEvent(NavigationEventConsumed)
    }
}
