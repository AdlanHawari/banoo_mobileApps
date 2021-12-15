package com.dev.banoo10.feature_personalForm.presentation.personal_form

data class PersonalFormState(
    val itemState: List<PersonalFormClass> = personalFormItem,
    val gender: String = "",
    val name: String = "",
    val age: String = "",
    val address: String = "",
    val pond_shape: String = "",
    val pond_width: Float? = null,
    val pond_length: Float? = null,
    val pond_depth: Float? = null,
//    val currentpage: Int = 0,
//    val text: String = "",
//    val hint: String = "",
//    val isHintVisible: Boolean = true,
//    val isLoading: Boolean = false,
//    val isButtonEnable: Boolean = false
)