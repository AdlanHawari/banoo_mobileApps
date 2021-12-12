package com.dev.banoo10.feature_auth.presentation.phone_form

data class PhoneFormState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true,
    val isLoading: Boolean = false,
    val isButtonEnable: Boolean = false
)
