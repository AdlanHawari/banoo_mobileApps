package com.dev.banoo10.feature_calculatorList.domain.use_case.get_calculator

import android.util.Log
import com.dev.banoo10.core.Resource
import com.dev.banoo10.core.constants.HttpRoutes
import com.dev.banoo10.feature_calculatorList.data.remote.dto.get_calculatorList.GetCalcListResponse
import com.dev.banoo10.feature_calculatorList.domain.repository.CalculatorRepo
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCalculatorUseCase @Inject constructor(
    private val repo: CalculatorRepo
) {

    suspend operator fun invoke(): Flow<Resource<List<GetCalcListResponse>>> = flow{

        try {
            emit(Resource.Loading<List<GetCalcListResponse>>())
            val token = repo.getAccessToken()
            val resp = repo.getCalculatorList(token)
            Log.e("use_case", resp.toString())

            emit(Resource.Success<List<GetCalcListResponse>>(resp))


        }catch(e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            emit(Resource.Error<List<GetCalcListResponse>>(
                message = "Redirected"
            ))

        } catch(e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            emit(Resource.Error<List<GetCalcListResponse>>(
                message = "Client Error"
            ))

        } catch(e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            emit(Resource.Error<List<GetCalcListResponse>>(
                message = "Server Error"
            ))

        } catch(e: Exception) {
            println("Error: ${e.message}")
            emit(Resource.Error<List<GetCalcListResponse>>(
                message = "Terjadi kesalahan. Periksa koneksi anda"
            ))

        }
    }
}