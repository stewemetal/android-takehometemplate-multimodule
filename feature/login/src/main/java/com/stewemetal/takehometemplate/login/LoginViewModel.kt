package com.stewemetal.takehometemplate.login

import com.stewemetal.takehometemplate.login.LoginViewEvent.LoginClicked
import com.stewemetal.takehometemplate.login.LoginViewEvent.PasswordTextUpdate
import com.stewemetal.takehometemplate.login.LoginViewEvent.UserTextUpdate
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
            it.copy(name = updatedUser)
        }
    }

    private fun onPasswordInputChange(updatedPassword: String) {
        emitNewState {
            it.copy(password = updatedPassword)
        }
    }

    private fun login() {
        emitNewState {
            // userUseCase.login(it.name, it.password)
            it.copy(isLoading = !it.isLoading)
        }
    }
}
