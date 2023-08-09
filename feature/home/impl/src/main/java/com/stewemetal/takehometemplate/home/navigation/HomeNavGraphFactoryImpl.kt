package com.stewemetal.takehometemplate.home.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stewemetal.takehometemplate.home.contract.HomeNavGraphFactory
import com.stewemetal.takehometemplate.home.contract.HomeRoute
import com.stewemetal.takehometemplate.home.ui.HomeScreen
import com.stewemetal.takehometemplate.home.ui.HomeViewModel
import org.koin.androidx.compose.koinViewModel

internal class HomeNavGraphFactoryImpl : HomeNavGraphFactory {
    override fun buildNavGraph(
        builder: NavGraphBuilder,
        onNavigateBack: () -> Unit,
        onNavigateToDetailsScreen: (String) -> Unit,
    ) {
        builder.composable(HomeRoute) {
            val viewModel: HomeViewModel = koinViewModel()
            val state = viewModel.state.collectAsState()

            HomeScreen(
                state = state.value,
                onBackClick = onNavigateBack,
                onItemClick = onNavigateToDetailsScreen
            )
        }
    }
}
