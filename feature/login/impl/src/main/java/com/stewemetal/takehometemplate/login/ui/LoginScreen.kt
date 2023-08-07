package com.stewemetal.takehometemplate.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.stewemetal.takehometemplate.login.LoginViewEvent
import com.stewemetal.takehometemplate.login.LoginViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
) {
    val state = viewModel.state.collectAsState()

    LoginScreenImpl(
        state = state.value,
        onUserChanged = { viewModel.triggerViewEvent(LoginViewEvent.UserTextUpdate(it)) },
        onPasswordChanged = { viewModel.triggerViewEvent(LoginViewEvent.PasswordTextUpdate(it)) },
        onLoginClick = { viewModel.triggerViewEvent(LoginViewEvent.LoginClicked) },
    )
}
