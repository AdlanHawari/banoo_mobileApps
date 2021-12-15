package com.dev.banoo10.feature_personalForm.presentation.personal_form

import com.dev.banoo10.feature_auth.presentation.phone_form.PhoneFormEvent

sealed class PersonalFormEvent {
    data class EnteredGender(val gender: String): PersonalFormEvent()
    data class EnteredName(val name: String): PersonalFormEvent()
    data class EnteredAge(val age: String): PersonalFormEvent()
    data class EnteredAddress(val address: String): PersonalFormEvent()
    data class Next(val currentPage:Int): PersonalFormEvent()
//    object Next: PersonalFormEvent()
}