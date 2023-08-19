package com.stewemetal.takehometemplate.login.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stewemetal.takehometemplate.login.contract.LoginNavGraphFactory
import com.stewemetal.takehometemplate.login.contract.LoginRoute
import com.stewemetal.takehometemplate.login.ui.LoginScreen
import com.stewemetal.takehometemplate.login.ui.LoginViewEvent.PasswordTextUpdate
import com.stewemetal.takehometemplate.login.ui.LoginViewEvent.UserTextUpdate
import com.stewemetal.takehometemplate.login.ui.LoginViewModel
import org.koin.androidx.compose.koinViewModel

internal class LoginNavGraphFactoryImpl : LoginNavGraphFactory {
    override fun buildNavGraph(
        navGraphBuilder: NavGraphBuilder,
        onNavigateToHomeScreen: () -> Unit,
    ) {
        navGraphBuilder.composable(LoginRoute) {
            val viewModel: LoginViewModel = koinViewModel()
            val state = viewModel.state.collectAsState()

            LoginScreen(
                state = state.value,
                onUserChanged = {
                    viewModel.triggerViewEvent(UserTextUpdate(it))
                },
                onPasswordChanged = {
                    viewModel.triggerViewEvent(PasswordTextUpdate(it))
                },
                onLoginClick = onNavigateToHomeScreen,
            )
        }
    }
}
