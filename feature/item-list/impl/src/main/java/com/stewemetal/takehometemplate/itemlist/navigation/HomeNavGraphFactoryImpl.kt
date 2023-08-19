package com.stewemetal.takehometemplate.itemlist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stewemetal.takehometemplate.itemlist.contract.HomeNavGraphFactory
import com.stewemetal.takehometemplate.itemlist.contract.HomeRoute
import com.stewemetal.takehometemplate.itemlist.ui.HomeNavigationEvent
import com.stewemetal.takehometemplate.itemlist.ui.HomeNavigationEvent.NavigateBack
import com.stewemetal.takehometemplate.itemlist.ui.HomeNavigationEvent.NavigateToItemDetails
import com.stewemetal.takehometemplate.itemlist.ui.HomeScreen
import com.stewemetal.takehometemplate.itemlist.ui.HomeState
import com.stewemetal.takehometemplate.itemlist.ui.HomeViewEvent.BackClicked
import com.stewemetal.takehometemplate.itemlist.ui.HomeViewEvent.ItemClicked
import com.stewemetal.takehometemplate.itemlist.ui.HomeViewModel
import com.stewemetal.takehometemplate.shell.domain.model.ItemId
import org.koin.androidx.compose.koinViewModel

internal class HomeNavGraphFactoryImpl : HomeNavGraphFactory {
    override fun buildNavGraph(
        navGraphBuilder: NavGraphBuilder,
        onNavigateBack: () -> Unit,
        onNavigateToDetailsScreen: (ItemId) -> Unit,
    ) {
        navGraphBuilder.composable(HomeRoute) {
            val viewModel: HomeViewModel = koinViewModel()
            val state by viewModel.state.collectAsState()

            state.ConsumeNavigationEvent(viewModel) { navigationEvent ->
                when (navigationEvent) {
                    is NavigateToItemDetails -> {
                        onNavigateToDetailsScreen(navigationEvent.itemId)
                        viewModel.onNavigationEventConsumed()
                    }

                    is NavigateBack -> {
                        onNavigateBack()
                        viewModel.onNavigationEventConsumed()
                    }
                }
            }

            HomeScreen(
                state = state,
                onAppBarNavigationClick = {
                    viewModel.triggerViewEvent(BackClicked)
                },
                onItemClick = {
                    viewModel.triggerViewEvent(ItemClicked(it))
                }
            )
        }
    }

    @Composable
    private fun HomeState.ConsumeNavigationEvent(
        viewModel: HomeViewModel,
        block: (HomeNavigationEvent) -> Unit,
    ) {
        LaunchedEffect(this) {
            if (navigationEvent != null) {
                block(navigationEvent)
                viewModel.onNavigationEventConsumed()
            }
        }
    }
}
