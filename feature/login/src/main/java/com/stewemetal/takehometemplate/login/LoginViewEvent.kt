package com.stewemetal.takehometemplate.login

sealed interface LoginViewEvent {
    data object LoginClicked : LoginViewEvent

    data class UserTextUpdate(
        val updatedUser: String,
    ): LoginViewEvent

    data class PasswordTextUpdate(
        val updatedPassword: String,
    ): LoginViewEvent
}
