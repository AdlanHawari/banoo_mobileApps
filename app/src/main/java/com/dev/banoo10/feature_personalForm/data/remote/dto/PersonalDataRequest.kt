package com.dev.banoo10.feature_personalForm.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PersonalDataRequest(
    val address: String? =null,
    val age: Int? =null,
    val gender: String? =null,
    val name: String? =null,
    val pond_depth: Float? =null,
    val pond_length: Float? =null,
    val pond_shape: String? =null,
    val pond_width: Float? =null
)