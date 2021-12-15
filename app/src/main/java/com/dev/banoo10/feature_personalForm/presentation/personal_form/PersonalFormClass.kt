package com.dev.banoo10.feature_personalForm.presentation.personal_form

data class PersonalFormClass(
    val title: String,
    val subtitle: String
//    val component: @Composable () -> Unit = {}
)

val personalFormItem = listOf(
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


