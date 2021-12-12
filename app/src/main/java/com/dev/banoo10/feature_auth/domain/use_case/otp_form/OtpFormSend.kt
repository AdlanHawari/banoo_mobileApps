package com.dev.banoo10.feature_auth.domain.use_case.otp_form

import android.util.Log
import com.dev.banoo10.core.Resource
import com.dev.banoo10.feature_auth.data.remote.dto.req_otp.ReqOTPResponse
import com.dev.banoo10.feature_auth.data.remote.dto.send_otp.SendOTPRequest
import com.dev.banoo10.feature_auth.domain.model.UserModel
import com.dev.banoo10.feature_auth.domain.repository.AuthRepo
import io.ktor.client.features.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OtpFormSend @Inject constructor(
    private val repo: AuthRepo
) {
    suspend operator fun invoke(sendOTPRequest: SendOTPRequest): Flow<Resource<UserModel>> = flow {
        try {
            emit(Resource.Loading<UserModel>())
            val resp = repo.postOTP(sendOTPRequest)
            repo.addUserLocal(resp)
//            Log.e("respon", resp.toString())
//            repo.addUserLocal()

            emit(Resource.Success<UserModel>(resp))
        }catch(e: RedirectResponseException) {
            // 3xx - responses
            Log.e("usecase",e.response.status.description)
            emit(Resource.Error<UserModel>(""))
//            println("Error: ${e.response.status.description}")
        } catch(e: ClientRequestException) {
            // 4xx - responses
//            println("Error: ${e.response.status.description}")
            Log.e("usecase",e.response.status.description)
            emit(Resource.Error<UserModel>("Client Error"))
            null
        } catch(e: ServerResponseException) {
            // 5xx - responses
//            println("Error: ${e.response.status.description}")
            Log.e("usecase",e.response.status.description)
            emit(Resource.Error<UserModel>("Server Error"))
//            null
        } catch(e: Exception) {
//            println("Error: ${e.message}")
            Log.e("huhuhu",e.localizedMessage)
            emit(Resource.Error<UserModel>("Terjadi kesalahan. Periksa koneksi anda"))
//            null
        }
    }
}