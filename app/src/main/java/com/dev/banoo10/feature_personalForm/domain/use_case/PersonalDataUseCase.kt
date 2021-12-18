package com.dev.banoo10.feature_personalForm.domain.use_case

import android.util.Log
import com.dev.banoo10.core.Resource
import com.dev.banoo10.feature_auth.domain.model.UserModel
import com.dev.banoo10.feature_personalForm.data.remote.dto.PersonalDataRequest
import com.dev.banoo10.feature_personalForm.data.remote.dto.PersonalDataResponse
import com.dev.banoo10.feature_personalForm.domain.repository.PersonalDataRepo
import io.ktor.client.features.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PersonalDataUseCase @Inject constructor(
    private val repo: PersonalDataRepo
) {
    suspend operator fun invoke(
        personalDataRequest: PersonalDataRequest
    ): Flow<Resource<PersonalDataResponse>> = flow {
        try {
            emit(Resource.Loading<PersonalDataResponse>())
            val accToken = repo.getAccessToken()
//            val accToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6ImFhNGExNTMwLWIxMDMtNDE2YS1hZTNhLWY2NmI0NjAzM2UxNCIsImlhdCI6MTYzOTU4ODk5NX0.kPjGKAraBo2rVHtacJzwlANJfQ1fYDcLoVPLyWbQCqQ"
            val resp = repo.postPersonalData(personalDataRequest,accToken)

            repo.editPersonalDataLocal(resp)

            emit(Resource.Success<PersonalDataResponse>(resp))
            Log.e("use_case",resp.toString())

        }catch(e: RedirectResponseException) {
            // 3xx - responses
            Log.e("usecase",e.response.status.description)
            emit(Resource.Error<PersonalDataResponse>(""))
//            println("Error: ${e.response.status.description}")
        } catch(e: ClientRequestException) {
            // 4xx - responses
//            println("Error: ${e.response.status.description}")
            Log.e("usecase",e.response.status.description)
            emit(Resource.Error<PersonalDataResponse>("Client Error"))
            null
        } catch(e: ServerResponseException) {
            // 5xx - responses
//            println("Error: ${e.response.status.description}")
            Log.e("usecase",e.response.status.description)
            emit(Resource.Error<PersonalDataResponse>("Server Error"))
//            null
        } catch(e: Exception) {
//            println("Error: ${e.message}")
            Log.e("huhuhu",e.localizedMessage)
            emit(Resource.Error<PersonalDataResponse>("Terjadi kesalahan. Periksa koneksi anda"))
//            null
        }
    }
}