package com.dev.banoo10.feature_calculatorList.domain.use_case.delete_calculator

import android.util.Log
import com.dev.banoo10.core.Resource
import com.dev.banoo10.feature_calculatorList.data.remote.dto.delete_calculator.DeleteCalcResponse
import com.dev.banoo10.feature_calculatorList.domain.model.CalcListModel
import com.dev.banoo10.feature_calculatorList.domain.repository.CalculatorRepo
import io.ktor.client.features.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteCalculatorUseCase @Inject constructor(
    private val repo: CalculatorRepo
) {

    suspend operator fun invoke(calcId: String): Flow<Resource<DeleteCalcResponse>> = flow {
        try {
            emit(Resource.Loading<DeleteCalcResponse>())
            val token = repo.getAccessToken()
            val resp = repo.getDeleteCalculatorbyId(token, calcId)
            Log.e("use_case", resp.toString())

            if (resp.deleted>0){
                repo.deleteLocalCalculator(calcId)
//                repo.getLocalCalculatorList()
            }
            emit(Resource.Success<DeleteCalcResponse>(resp))




        }catch(e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            Log.e("error msg",e.localizedMessage)
            emit(Resource.Error<DeleteCalcResponse>(
                message = "Redirected"
            ))

        } catch(e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            Log.e("error msg",e.localizedMessage)
            emit(Resource.Error<DeleteCalcResponse>(
                message = "Client Error"
            ))

        } catch(e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            Log.e("error msg",e.localizedMessage)
            emit(Resource.Error<DeleteCalcResponse>(
                message = "Server Error"
            ))

        } catch(e: Exception) {
            println("Error: ${e.message}")
            Log.e("error msg",e.localizedMessage)
            emit(Resource.Error<DeleteCalcResponse>(
                message = "Terjadi kesalahan. Periksa koneksi anda"
            ))

        }

    }
}