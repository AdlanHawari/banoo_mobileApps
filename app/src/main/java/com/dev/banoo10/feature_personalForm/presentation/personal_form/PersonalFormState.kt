package com.dev.banoo10.feature_personalForm.presentation.personal_form

data class PersonalFormState(
    val itemState: List<PersonalFormClass> = personalFormItem,
//    val resultState: List<PersonalFormResult> = emptyList()
    val gender: String = "",
    val name: String = "",
    val age: String = "",
    val address: String = "",
    val pond_shape: String = "",
    val pond_width: String = "",
    val pond_length: String = "",
    val pond_depth: String = "",
    val currentpage: Int = 0,
    val text: String = "",
    val hint: String = "",
    val isLoading: Boolean = false,
)