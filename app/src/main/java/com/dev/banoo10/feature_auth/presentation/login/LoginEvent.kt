package com.dev.banoo10.feature_auth.presentation.login

sealed class LoginEvent{
    object LoginClicked: LoginEvent()
    object ShowClicked: LoginEvent()
    object LogoutClicked: LoginEvent()
}
