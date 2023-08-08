package com.stewemetal.takehometemplate.login.ui

sealed interface LoginViewEvent {
    data object LoginClicked : LoginViewEvent

    data class UserTextUpdate(
        val updatedUser: String,
    ) : LoginViewEvent

    data class PasswordTextUpdate(
        val updatedPassword: String,
    ) : LoginViewEvent
}
