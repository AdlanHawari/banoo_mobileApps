package com.dev.banoo10.feature_personalForm.presentation.personal_form

data class PersonalFormClass(
    val title: String,
    val subtitle: String,
    val options: List<String> = emptyList()
//    val component: @Composable () -> Unit = {}
)

data class PersonalFormResult(
    val gender: String = "",
    val name: String = "",
    val age: String = "",
    val address: String = "",
    val pond_shape: String = "",
    val pond_width: Float? = null,
    val pond_length: Float? = null,
    val pond_depth: Float? = null,
)


val personalFormItem = listOf(
    PersonalFormClass(
        "Jenis Kelamin",
        "Pilih Jenis Kelamin Anda",
        listOf(
            "Pria",
            "Wanita"
        )

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
        "Bentuk Kolam Anda",
        listOf(
            "Lingkaran",
            "Persegi Panjang"
        )
    ),

    PersonalFormClass(
        "Selesai",
        ""
    )
)


