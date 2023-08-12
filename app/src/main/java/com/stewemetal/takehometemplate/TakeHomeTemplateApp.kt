package com.stewemetal.takehometemplate

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
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
                navController.doNavigate {
                    navigateToHome {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                    graph.setStartDestination(HomeRoute)
                }
            },
        )

        homeNavGraphFactory.buildNavGraph(
            builder = this,
            onNavigateBack = {
                navController.doNavigate {
                    navigateUp()
                }
            },
            onNavigateToDetailsScreen = { itemId ->
                navController.doNavigate {
                    navigateToItemDetails(itemId)
                }
            },
        )

        itemDetailsNavGraphFactory.buildNavGraph(
            builder = this,
            onNavigateBack = {
                navController.doNavigate {
                    navigateUp()
                }
            },
        )
    }
}

private fun NavBackStackEntry.lifecycleIsResumed(): Boolean =
    this.lifecycle.currentState == Lifecycle.State.RESUMED

private fun NavController.canNavigate(): Boolean =
    currentBackStackEntry?.lifecycleIsResumed() ?: false

private fun NavController.doNavigate(block: NavController.() -> Unit) {
    if (canNavigate()) {
        block()
    }
}
