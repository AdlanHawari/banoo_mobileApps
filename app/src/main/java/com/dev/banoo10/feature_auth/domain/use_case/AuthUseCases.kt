package com.dev.banoo10.feature_auth.domain.use_case

import com.dev.banoo10.feature_auth.domain.use_case.login.AddUserLocal
import com.dev.banoo10.feature_auth.domain.use_case.login.DeleteUserLocal
import com.dev.banoo10.feature_auth.domain.use_case.login.GetAccTokenLocal
import com.dev.banoo10.feature_auth.domain.use_case.login.GetUserLocal
import com.dev.banoo10.feature_auth.domain.use_case.otp_form.OtpFormSend
import com.dev.banoo10.feature_auth.domain.use_case.phone_form.PhoneFormSend

data class AuthUseCases(
    val addUserLocal: AddUserLocal,
    val getUserLocal: GetUserLocal,
    val getAccTokenLocal: GetAccTokenLocal,
    val deleteUserLocal: DeleteUserLocal,
    val phoneFormSend: PhoneFormSend,
    val otpFormSend: OtpFormSend
)