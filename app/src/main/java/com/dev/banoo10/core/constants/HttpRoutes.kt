package com.dev.banoo10.core.constants

object HttpRoutes {
//    private const val BASE_URL = "http://127.0.0.1:3000"
    //ofish
    private const val BASE_URL = "http://192.168.8.135:3000"

    //tether
//    private const val BASE_URL = "http://192.168.43.103:3000"
    const val REQ_OTP_URL = "$BASE_URL/auth/reqotp"
    const val SEND_OTP_URL = "$BASE_URL/auth/login"
    const val REQ_EDIT_PROFILE_URL = "$BASE_URL/profile/edit"
    const val GET_CALCULATOR_URL = "$BASE_URL/feedcalc"
    const val ADD_CALCULATOR_URL = "$BASE_URL/feedcalc/create"
    const val DELETE_CALCULATOR_URL = "$BASE_URL/feedcalc/delete"
}