package com.dev.banoo10.feature_personalForm.presentation.personal_form

import com.dev.banoo10.feature_personalForm.presentation.personal_form.component.PersonalFormIndicator

data class PersonalFormClass(
    val title: String,
    val subtitle: String
)

val personalFormItem by lazy {
    listOf(
        PersonalFormClass(
            "Jenis Kelamin",
            "Pilih Jenis Kelamin Anda"
        ),
        PersonalFormClass(
            "Nama",
            "Nama Anda"
        ),
        PersonalFormClass(
            "Usia",
            "Berapa Usia Anda?"
        ),
        PersonalFormClass(
            "Alamat",
            "Alamat Anda"
        ),
        PersonalFormClass(
            "Bentuk Kolam",
            "Bentuk Kolam Anda"
        ),

        PersonalFormClass(
            "Selesai",
            ""
        )
    )
}


