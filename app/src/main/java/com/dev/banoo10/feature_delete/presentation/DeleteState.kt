package com.dev.banoo10.feature_delete.presentation

data class DeleteState(
    val isLoading: Boolean = false,
    val token: String = "",
    val error: String = "",
)