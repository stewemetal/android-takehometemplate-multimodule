package com.stewemetal.takehometemplate.home.ui

import com.stewemetal.takehometemplate.shell.architecture.BaseViewModel
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel : BaseViewModel<HomeViewEvent, HomeState>(
    HomeState()
) {
    override fun onViewEvent(event: HomeViewEvent) {
        // TODO
    }
}
