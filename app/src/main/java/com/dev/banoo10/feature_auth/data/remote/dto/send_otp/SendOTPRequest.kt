package com.dev.banoo10.feature_auth.data.remote.dto.send_otp

import kotlinx.serialization.Serializable

@Serializable
data class SendOTPRequest(
    val phone: String,
    val code: String
)
