package com.stewemetal.takehometemplate.itemlist.ui

import androidx.lifecycle.viewModelScope
import com.stewemetal.takehometemplate.itemlist.ui.HomeNavigationEvent.NavigateBack
import com.stewemetal.takehometemplate.itemlist.ui.HomeNavigationEvent.NavigateToItemDetails
import com.stewemetal.takehometemplate.itemlist.ui.HomeViewEvent.BackClicked
import com.stewemetal.takehometemplate.itemlist.ui.HomeViewEvent.ItemClicked
import com.stewemetal.takehometemplate.itemlist.ui.HomeViewEvent.NavigationEventConsumed
import com.stewemetal.takehometemplate.shell.architecture.BaseViewModel
import com.stewemetal.takehometemplate.shell.domain.usecase.GetItemsUseCase
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import timber.log.Timber

@KoinViewModel
internal class HomeViewModel(
    private val getItemsUseCase: GetItemsUseCase,
) : BaseViewModel<HomeViewEvent, HomeState>(
    HomeState()
) {

    init {
        viewModelScope.launch {
            val items = getItemsUseCase.getItems()
            emitNewState {
                HomeState(
                    isLoading = false,
                    items = items,
                )
            }
        }
    }

    override fun onViewEvent(event: HomeViewEvent) {
        Timber.e(">>> HomeVM onViewEvent $event")
        when (event) {
            is ItemClicked -> {
                emitNewState {
                    copy(navigationEvent = NavigateToItemDetails(event.itemId))
                }
            }

            BackClicked -> {
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
