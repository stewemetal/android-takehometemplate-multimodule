package com.stewemetal.takehometemplate.itemlist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stewemetal.takehometemplate.itemlist.contract.ItemListNavGraphFactory
import com.stewemetal.takehometemplate.itemlist.contract.ItemListRoute
import com.stewemetal.takehometemplate.itemlist.ui.HomeNavigationEvent
import com.stewemetal.takehometemplate.itemlist.ui.HomeNavigationEvent.NavigateBack
import com.stewemetal.takehometemplate.itemlist.ui.HomeNavigationEvent.NavigateToItemDetails
import com.stewemetal.takehometemplate.itemlist.ui.ItemListScreen
import com.stewemetal.takehometemplate.itemlist.ui.ItemListState
import com.stewemetal.takehometemplate.itemlist.ui.ItemListViewEvent.AppBarNavigationClicked
import com.stewemetal.takehometemplate.itemlist.ui.ItemListViewEvent.ItemClicked
import com.stewemetal.takehometemplate.itemlist.ui.ItemListViewModel
import com.stewemetal.takehometemplate.shell.domain.model.ItemId
import org.koin.androidx.compose.koinViewModel
import org.koin.core.annotation.Singleton

@Singleton
internal class ItemListNavGraphFactoryImpl : ItemListNavGraphFactory {
    override fun buildNavGraph(
        navGraphBuilder: NavGraphBuilder,
        onNavigateBack: () -> Unit,
        onNavigateToDetailsScreen: (ItemId) -> Unit,
    ) {
        navGraphBuilder.composable(ItemListRoute) {
            val viewModel: ItemListViewModel = koinViewModel()
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

            ItemListScreen(
                state = state,
                onAppBarNavigationClick = {
                    viewModel.triggerViewEvent(AppBarNavigationClicked)
                },
                onItemClick = {
                    viewModel.triggerViewEvent(ItemClicked(it))
                }
            )
        }
    }

    @Composable
    private fun ItemListState.ConsumeNavigationEvent(
        viewModel: ItemListViewModel,
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
