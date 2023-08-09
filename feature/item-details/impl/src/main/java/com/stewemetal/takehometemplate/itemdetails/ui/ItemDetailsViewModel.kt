package com.stewemetal.takehometemplate.itemdetails.ui

import com.stewemetal.takehometemplate.shell.architecture.BaseViewModel
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ItemDetailsViewModel : BaseViewModel<ItemDetailsViewEvent, ItemDetailsState>(
    ItemDetailsState()
) {
    override fun onViewEvent(event: ItemDetailsViewEvent) {
        // TODO
    }
}
