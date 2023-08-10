package com.stewemetal.takehometemplate.login.ui

import com.stewemetal.takehometemplate.login.ui.LoginViewEvent.LoginClicked
import com.stewemetal.takehometemplate.login.ui.LoginViewEvent.PasswordTextUpdate
import com.stewemetal.takehometemplate.login.ui.LoginViewEvent.UserTextUpdate
import com.stewemetal.takehometemplate.shell.architecture.BaseViewModel
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel(
    // val userUseCase: UserUseCase
) : BaseViewModel<LoginViewEvent, LoginState>(
    LoginState()
) {
    override fun onViewEvent(event: LoginViewEvent) {
        when (event) {
            LoginClicked -> login()
            is UserTextUpdate -> onUserInputChange(event.updatedUser)
            is PasswordTextUpdate -> onPasswordInputChange(event.updatedPassword)
        }
    }

    private fun onUserInputChange(updatedUser: String) {
        emitNewState {
            copy(name = updatedUser)
        }
    }

    private fun onPasswordInputChange(updatedPassword: String) {
        emitNewState {
            copy(password = updatedPassword)
        }
    }

    private fun login() {
        emitNewState {
            // userUseCase.login(it.name, it.password)
            copy(isLoading = !isLoading)
        }
    }
}
