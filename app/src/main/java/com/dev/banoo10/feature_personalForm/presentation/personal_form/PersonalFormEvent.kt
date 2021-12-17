package com.dev.banoo10.feature_personalForm.presentation.personal_form

import com.dev.banoo10.feature_auth.presentation.phone_form.PhoneFormEvent

sealed class PersonalFormEvent {
    data class EnteredGender(val gender: String): PersonalFormEvent()
    data class EnteredName(val name: String): PersonalFormEvent()
    data class EnteredAge(val age: String): PersonalFormEvent()
    data class EnteredAddress(val address: String): PersonalFormEvent()
    data class EnteredPondShape(val pond_shape: String): PersonalFormEvent()
    data class EnteredPondDepth(val pond_depth: String): PersonalFormEvent()
    data class EnteredPondLength(val pond_length: String): PersonalFormEvent()
    data class EnteredPondWidth(val pond_width: String): PersonalFormEvent()
    object Finish: PersonalFormEvent()
//    object Next: PersonalFormEvent()
}