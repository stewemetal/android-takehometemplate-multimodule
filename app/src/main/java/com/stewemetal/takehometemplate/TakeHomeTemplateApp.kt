package com.stewemetal.takehometemplate

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.stewemetal.takehometemplate.home.contract.HomeNavGraphFactory
import com.stewemetal.takehometemplate.home.contract.HomeRoute
import com.stewemetal.takehometemplate.home.contract.navigateToHome
import com.stewemetal.takehometemplate.itemdetails.contract.ItemDetailsNavGraphFactory
import com.stewemetal.takehometemplate.itemdetails.contract.navigateToItemDetails
import com.stewemetal.takehometemplate.login.contract.LoginNavGraphFactory
import com.stewemetal.takehometemplate.login.contract.LoginRoute
import com.stewemetal.takehometemplate.shell.navigation.compose.TakeHomeTemplateNavHost
import org.koin.compose.koinInject

@Composable
fun TakeHomeTemplateApp(
    modifier: Modifier = Modifier,
    loginNavGraphFactory: LoginNavGraphFactory = koinInject(),
    homeNavGraphFactory: HomeNavGraphFactory = koinInject(),
    itemDetailsNavGraphFactory: ItemDetailsNavGraphFactory = koinInject(),
) {
    val navController = rememberNavController()
    TakeHomeTemplateNavHost(
        navController = navController,
        startDestination = LoginRoute,
        modifier = modifier,
    ) {
        loginNavGraphFactory.buildNavGraph(
            builder = this,
            onNavigateToHomeScreen = {
                navController.navigateToHome {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
                navController.graph.setStartDestination(HomeRoute)
            },
        )

        homeNavGraphFactory.buildNavGraph(
            builder = this,
            onNavigateBack = {
                navController.navigateUp()
            },
            onNavigateToDetailsScreen = { itemId ->
                navController.navigateToItemDetails(itemId)
            },
        )

        itemDetailsNavGraphFactory.buildNavGraph(
            builder = this,
            onNavigateBack = {
                navController.navigateUp()
            },
        )
    }
}
