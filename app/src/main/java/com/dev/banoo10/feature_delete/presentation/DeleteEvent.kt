package com.dev.banoo10.feature_delete.presentation

sealed class DeleteEvent{
    object ProfileClicked: DeleteEvent()
    object ShowClicked: DeleteEvent()
    object LogoutClicked: DeleteEvent()
}