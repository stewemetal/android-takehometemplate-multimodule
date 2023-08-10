package com.stewemetal.takehometemplate.home.ui

import androidx.lifecycle.viewModelScope
import com.stewemetal.takehometemplate.shell.architecture.BaseViewModel
import com.stewemetal.takehometemplate.shell.domain.GetItemsUseCase
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
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
        // TODO
    }
}
