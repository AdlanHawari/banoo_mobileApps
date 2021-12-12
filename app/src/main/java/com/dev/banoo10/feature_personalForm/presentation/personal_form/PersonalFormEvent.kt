package com.dev.banoo10.feature_personalForm.presentation.personal_form

import com.dev.banoo10.feature_auth.presentation.phone_form.PhoneFormEvent

sealed class PersonalFormEvent {
    data class Next(val currentPage:Int): PersonalFormEvent()
//    object Next: PersonalFormEvent()
}