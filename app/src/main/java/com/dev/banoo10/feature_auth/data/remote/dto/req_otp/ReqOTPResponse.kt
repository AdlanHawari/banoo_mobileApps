package com.dev.banoo10.feature_auth.data.remote.dto.req_otp

import kotlinx.serialization.Serializable

@Serializable
data class ReqOTPResponse(
    val to: String,
    val status: String,
    val provider: String,
    val createdAt: String,
    val updatedAt: String
)
