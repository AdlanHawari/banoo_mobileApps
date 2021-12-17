package com.dev.banoo10.core.constants

object HttpRoutes {
    private const val BASE_URL = "http://192.168.8.135:3000"
    //    private const val BASE_URL = "http://192.168.1.232:3000"
    const val REQ_OTP_URL = "$BASE_URL/auth/reqotp"
    const val SEND_OTP_URL = "$BASE_URL/auth/login"
    const val REQ_EDIT_PROFILE_URL = "$BASE_URL/profile/edit"
}