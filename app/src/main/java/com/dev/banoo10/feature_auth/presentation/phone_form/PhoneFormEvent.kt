package com.dev.banoo10.feature_auth.presentation.phone_form

import androidx.compose.ui.focus.FocusState

sealed class PhoneFormEvent{
    data class EnteredPhone(val value:String): PhoneFormEvent()
    data class ChangePhoneFocus(val focusState: FocusState): PhoneFormEvent()
    object SendPhone: PhoneFormEvent()
}
