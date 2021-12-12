package com.dev.banoo10.feature_auth.data.remote.dto.req_otp

import kotlinx.serialization.Serializable

@Serializable
data class ReqOTPRequest(
    val phone: String
)
