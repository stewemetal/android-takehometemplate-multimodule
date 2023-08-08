package com.stewemetal.takehometemplate.login.ui

data class LoginState(
    val isLoading: Boolean = false,
    val name: String = "",
    val password: String = "",
)
