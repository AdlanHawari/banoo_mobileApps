package com.dev.banoo10.feature_personalForm.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PersonalDataResponse (
    val id: String,
    val phone: String,
    val name: String,
    val gender: String,
    val age: Int,
    val address: String,
    val species: String,
    val pond_shape: String,
    val pond_length: Float,
    val pond_width: Float,
    val pond_depth: Float,
    val createdAt: String,
    val updatedAt: String
)