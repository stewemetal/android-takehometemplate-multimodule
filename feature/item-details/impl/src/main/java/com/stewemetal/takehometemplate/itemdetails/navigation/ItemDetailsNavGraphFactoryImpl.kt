package com.stewemetal.takehometemplate.itemdetails.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stewemetal.takehometemplate.itemdetails.contract.ItemDetailsItemIdArg
import com.stewemetal.takehometemplate.itemdetails.contract.ItemDetailsNavGraphFactory
import com.stewemetal.takehometemplate.itemdetails.contract.ItemDetailsRoute
import com.stewemetal.takehometemplate.itemdetails.ui.ItemDetailsScreen
import com.stewemetal.takehometemplate.itemdetails.ui.ItemDetailsViewModel
import org.koin.androidx.compose.koinViewModel

internal class ItemDetailsNavGraphFactoryImpl : ItemDetailsNavGraphFactory {
    override fun buildNavGraph(
        navGraphBuilder: NavGraphBuilder,
        onNavigateBack: () -> Unit,
    ) {
        navGraphBuilder.composable("$ItemDetailsRoute/{$ItemDetailsItemIdArg}") {
            val viewModel: ItemDetailsViewModel = koinViewModel()
            val state = viewModel.state.collectAsState()

            ItemDetailsScreen(
                state = state.value,
                onBackClick = onNavigateBack,
            )
        }
    }
}
