package com.dev.banoo10.feature_personalForm.presentation.personal_form

data class PhoneFormState(
    val currentpage: Int = 0,
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true,
    val isLoading: Boolean = false,
    val isButtonEnable: Boolean = false
)