package com.stewemetal.takehometemplate.home.ui

import com.stewemetal.takehometemplate.shell.architecture.BaseViewModel
import com.stewemetal.takehometemplate.shell.domain.Item
import com.stewemetal.takehometemplate.shell.domain.ItemId
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel : BaseViewModel<HomeViewEvent, HomeState>(
    HomeState()
) {
    init {
        emitNewState {
            HomeState(
                items = listOf(
                    Item(ItemId(1u), "a"),
                    Item(ItemId(1u), "b"),
                    Item(ItemId(1u), "c"),
                )
            )
        }
    }

    override fun onViewEvent(event: HomeViewEvent) {
        // TODO
    }
}
