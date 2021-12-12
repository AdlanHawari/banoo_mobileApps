package com.dev.banoo10.feature_auth.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserModel (
    val accessToken: String,
    val address: String? =null,
    val age: Int? =null,
    val createdAt: String? =null,
    val gender: String? =null,
    val id: String,
    val name: String? =null,
    val phone: String? =null,
    val pond_depth: Float? =null,
    val pond_length: Float? =null,
    val pond_shape: String? =null,
    val pond_width: Float? =null,
    val species: String? =null,
    val updatedAt: String? =null

)