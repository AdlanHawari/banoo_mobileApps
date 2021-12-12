package com.dev.banoo10.feature_auth.presentation.otp_form

import androidx.compose.ui.focus.FocusState
import com.dev.banoo10.feature_auth.presentation.phone_form.PhoneFormEvent

sealed class OtpFormEvent{
    data class EnteredOTP(val value:String): OtpFormEvent()
    object ResendOTP: OtpFormEvent()
    object SendPhone: OtpFormEvent()
}
