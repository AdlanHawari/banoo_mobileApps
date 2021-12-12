package com.dev.banoo10.feature_auth.presentation.login


data class LoginState(
    val isLoading: Boolean = false,
    val token: String = "",
    val error: String = "",
)
