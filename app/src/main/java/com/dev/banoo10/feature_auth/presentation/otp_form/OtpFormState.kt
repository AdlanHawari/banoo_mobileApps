package com.dev.banoo10.feature_auth.presentation.otp_form

data class OtpFormState (
    val phone: String = "",
    val otp: String = "",
    val isLoading: Boolean = false,
    val isButtonEnable: Boolean = false,
    var time: String = "",
    val isTimerFinish: Boolean = false
)