package com.stewemetal.takehometemplate.itemdetails.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.stewemetal.takehometemplate.itemdetails.contract.ItemDetailsArgs
import com.stewemetal.takehometemplate.shell.architecture.BaseViewModel
import com.stewemetal.takehometemplate.shell.domain.model.ItemId
import com.stewemetal.takehometemplate.shell.domain.usecase.GetItemUseCase
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ItemDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val getItemUseCase: GetItemUseCase,
) : BaseViewModel<ItemDetailsViewEvent, ItemDetailsState>(
    ItemDetailsState()
) {

    private val args = ItemDetailsArgs(savedStateHandle)

    init {
        viewModelScope.launch {
            val item = getItemUseCase.getItem(ItemId(args.itemId))

            emitNewState {
                copy(
                    isLoading = false,
                    item = item,
                )
            }
        }
    }

    override fun onViewEvent(event: ItemDetailsViewEvent) {
        // TODO
    }
}
