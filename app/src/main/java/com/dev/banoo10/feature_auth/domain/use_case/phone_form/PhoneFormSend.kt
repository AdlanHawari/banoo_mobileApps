package com.dev.banoo10.feature_auth.domain.use_case.phone_form

import android.util.Log
import com.dev.banoo10.core.Resource
import com.dev.banoo10.feature_auth.data.remote.dto.req_otp.ReqOTPRequest
import com.dev.banoo10.feature_auth.data.remote.dto.req_otp.ReqOTPResponse
import com.dev.banoo10.feature_auth.domain.repository.AuthRepo
import io.ktor.client.features.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhoneFormSend @Inject constructor(
    private val repo: AuthRepo
) {
//    operator suspend fun invoke():

//    @Throws(InvalidTextFieldException::class)
    suspend operator fun invoke(reqOTPRequest: ReqOTPRequest) : Flow<Resource<ReqOTPResponse?>> = flow{
        if (reqOTPRequest.phone.isBlank()) {
//            throw InvalidTextFieldException("mana nomornya")
            Log.e("usecase","blank")
            emit(Resource.Error<ReqOTPResponse?>("Harap isi nomor"))
        }
        else if (reqOTPRequest.phone.first() == '0') {
//            throw InvalidTextFieldException("masukkan nomor tanpa angka 0 di depan")
            emit(Resource.Error<ReqOTPResponse?>("Masukkan nomor HP tanpa 0 di depan"))
        }
        else if (reqOTPRequest.phone.first() == '+') {
//            throw InvalidTextFieldException("masukkan nomor tanpa tanda + di depan")
            emit(Resource.Error<ReqOTPResponse?>("Masukkan nomor HP tanpa + di depan"))
        }
        else if (reqOTPRequest.phone.take(2) == "62") {
//            throw InvalidTextFieldException("masukkan nomor tanpa kode negara")
            emit(Resource.Error<ReqOTPResponse?>("Masukkan nomor HP tanpa kode negara"))
        }
        else if (reqOTPRequest.phone.length < 10) {
            emit(Resource.Error<ReqOTPResponse?>("Nomor HP tidak valid"))
        }
        else{
            try {
                emit(Resource.Loading<ReqOTPResponse?>())
                val resp = repo.postPhoneUser(ReqOTPRequest("+62${reqOTPRequest.phone}"))
                emit(Resource.Success<ReqOTPResponse?>(resp))
            }catch(e: RedirectResponseException) {
                // 3xx - responses
                Log.e("usecase",e.response.status.description)
                emit(Resource.Error<ReqOTPResponse?>(""))
//            println("Error: ${e.response.status.description}")
            } catch(e: ClientRequestException) {
                // 4xx - responses
//            println("Error: ${e.response.status.description}")
                Log.e("usecase",e.response.status.description)
                emit(Resource.Error<ReqOTPResponse?>("Client Error"))
                null
            } catch(e: ServerResponseException) {
                // 5xx - responses
//            println("Error: ${e.response.status.description}")
                Log.e("usecase",e.response.status.description)
                emit(Resource.Error<ReqOTPResponse?>("Server Error"))
//            null
            } catch(e: Exception) {
//            println("Error: ${e.message}")
                Log.e("huhuhu",e.localizedMessage)
                emit(Resource.Error<ReqOTPResponse?>("Terjadi kesalahan. Periksa koneksi anda"))
//            null
            }

        }


    }

//    @Throws(InvalidTextFieldException::class)
//    suspend operator fun invoke(reqOTPRequest: ReqOTPRequest) {
//        if (reqOTPRequest.phone.isBlank()) {
//            throw InvalidTextFieldException("mana nomornya")
//        }
//        if (reqOTPRequest.phone.first() == '0') {
//            throw InvalidTextFieldException("masukkan nomor tanpa angka 0 di depan")
//        }
//        if (reqOTPRequest.phone.first() == '+') {
//            throw InvalidTextFieldException("masukkan nomor tanpa tanda + di depan")
//        }
//        if (reqOTPRequest.phone.take(2) == "62") {
//            throw InvalidTextFieldException("masukkan nomor tanpa kode negara")
//        }
//        try {
//            repo.postPhoneUser(ReqOTPRequest("+62${reqOTPRequest.phone}"))
//
//        }catch(e: RedirectResponseException) {
//            // 3xx - responses
//                Log.e("usecase",e.response.status.description)
////            println("Error: ${e.response.status.description}")
//        } catch(e: ClientRequestException) {
//            // 4xx - responses
////            println("Error: ${e.response.status.description}")
//            Log.e("usecase",e.response.status.description)
//            null
//        } catch(e: ServerResponseException) {
//            // 5xx - responses
////            println("Error: ${e.response.status.description}")
//            Log.e("usecase",e.response.status.description)
////            null
//        } catch(e: Exception) {
////            println("Error: ${e.message}")
//            Log.e("usecase",e.localizedMessage)
////            null
//        }
//    }
}