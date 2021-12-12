package com.dev.banoo10.feature_auth.data.repository

import com.dev.banoo10.Database
import com.dev.banoo10.UserTable
import com.dev.banoo10.core.constants.HttpRoutes
import com.dev.banoo10.feature_auth.data.remote.dto.req_otp.ReqOTPRequest
import com.dev.banoo10.feature_auth.data.remote.dto.req_otp.ReqOTPResponse
import com.dev.banoo10.feature_auth.data.remote.dto.send_otp.SendOTPRequest
import com.dev.banoo10.feature_auth.domain.model.UserModel
import com.dev.banoo10.feature_auth.domain.repository.AuthRepo
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val client: HttpClient,
    private val database: Database
) : AuthRepo {

//        override fun getAccessToken(): SelectAccToken? {
//        return database.userQueryQueries.selectAccToken().executeAsOne()
//    }

    override fun getAccessToken(): String {
        return database.userQueryQueries.selectAccToken().executeAsOne()
    }

    override fun getUserLocal(): List<UserTable>{
        return database.userQueryQueries.selectAll().executeAsList()
    }

    override suspend fun addUserLocal(userModel: UserModel) {

        database.userQueryQueries.addUser(
            userModel.accessToken,
            userModel.address,
            userModel.age,
            userModel.createdAt,
            userModel.gender,
            userModel.id,
            userModel.name,
            userModel.phone,
            userModel.pond_depth,
            userModel.pond_length,
            userModel.pond_shape,
            userModel.pond_width,
            userModel.species,
            userModel.updatedAt,
        )

//        database.userQueryQueries.addUser(
//            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6ImU3YjNhYzNjLTRkNzctNGE2Zi1hODY3LTRmY2MyNjA5ZGMyNyIsImlhdCI6MTYzODcxNDcxNn0.eXl063IHeq19StzufGmj8CBupF3xfhXZxoNdrzQBxPk",
//            "alamat",
//            21,
//            "sekarang",
//            "cewe",
//            "ini id",
//            "endang",
//            "1231",
//            23f,
//            22f,
//            "kotak",
//            23f,
//            "koral",
//            "besok"
//        )

    }

    override suspend fun deleteUserLocal() {
        database.userQueryQueries.deleteUser()

    }

    override suspend fun postPhoneUser(reqOTPRequest: ReqOTPRequest): ReqOTPResponse? {
        return client.post<ReqOTPResponse> {
                url(HttpRoutes.REQ_OTP_URL)
                contentType(ContentType.Application.Json)
                body = reqOTPRequest
            }
//        return try {
//            client.post<ReqOTPResponse> {
//                url(HttpRoutes.REQ_OTP_URL)
//                contentType(ContentType.Application.Json)
//                body = reqOTPRequest
//            }
//
//        }catch(e: RedirectResponseException) {
//            // 3xx - responses
//            println("Error: ${e.response.status.description}")
//            null
//        } catch(e: ClientRequestException) {
//            // 4xx - responses
//            println("Error: ${e.response.status.description}")
//            null
//        } catch(e: ServerResponseException) {
//            // 5xx - responses
//            println("Error: ${e.response.status.description}")
//            null
//        } catch(e: Exception) {
//            println("Error: ${e.message}")
//            null
//        }
    }

    override suspend fun postOTP(sendOTPRequest: SendOTPRequest): UserModel {
        return client.post<UserModel> {
            url(HttpRoutes.SEND_OTP_URL)
            contentType(ContentType.Application.Json)
            body = sendOTPRequest
        }
    }
}