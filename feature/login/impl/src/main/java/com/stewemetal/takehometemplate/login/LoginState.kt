package com.stewemetal.takehometemplate.login

data class LoginState(
    val isLoading: Boolean = false,
    val name: String = "",
    val password: String = "",
)
