package com.dev.banoo10.feature_auth.domain.repository

import com.dev.banoo10.UserTable
import com.dev.banoo10.feature_auth.data.remote.dto.req_otp.ReqOTPRequest
import com.dev.banoo10.feature_auth.data.remote.dto.req_otp.ReqOTPResponse
import com.dev.banoo10.feature_auth.data.remote.dto.send_otp.SendOTPRequest
import com.dev.banoo10.feature_auth.domain.model.UserModel

interface AuthRepo {

    fun getUserLocal(): List<UserTable>

    fun getAccessToken(): String
//    fun getAccessToken(): Flow<Resource<SelectAccToken>>

    suspend fun addUserLocal( userModel: UserModel)

    suspend fun deleteUserLocal()

    suspend fun postPhoneUser( reqOTPRequest: ReqOTPRequest): ReqOTPResponse?

    suspend fun postOTP( sendOTPRequest: SendOTPRequest): UserModel



}