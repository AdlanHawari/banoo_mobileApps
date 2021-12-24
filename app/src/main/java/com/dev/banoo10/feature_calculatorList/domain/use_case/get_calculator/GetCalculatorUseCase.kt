package com.dev.banoo10.feature_calculatorList.domain.use_case.get_calculator

import android.util.Log
import com.dev.banoo10.FeedCalcs
import com.dev.banoo10.core.Resource
import com.dev.banoo10.core.constants.Constants.CULT_DAYS
import com.dev.banoo10.core.constants.HttpRoutes
import com.dev.banoo10.feature_calculatorList.data.remote.dto.get_calculatorList.GetCalcListResponse
import com.dev.banoo10.feature_calculatorList.domain.model.CalcListModel
import com.dev.banoo10.feature_calculatorList.domain.model.FeedCalcLocalModel
import com.dev.banoo10.feature_calculatorList.domain.model.SchedCalcLocalModel
import com.dev.banoo10.feature_calculatorList.domain.repository.CalculatorRepo
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GetCalculatorUseCase @Inject constructor(
    private val repo: CalculatorRepo
) {

    suspend operator fun invoke(): Flow<Resource<List<CalcListModel>>> = flow{

        try {
            emit(Resource.Loading<List<CalcListModel>>())
            val token = repo.getAccessToken()
            val resp = repo.getCalculatorList(token)
            Log.e("use_case", resp.toString())

//            val vmData = emptyList<CalcListModel>()
            updateLocalList(resp)
            val vmData = ConvertListRespon(resp)

            emit(Resource.Success<List<CalcListModel>>(vmData))


        }catch(e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            emit(Resource.Error<List<CalcListModel>>(
                message = "Redirected"
            ))

        } catch(e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            emit(Resource.Error<List<CalcListModel>>(
                message = "Client Error"
            ))

        } catch(e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            emit(Resource.Error<List<CalcListModel>>(
                message = "Server Error"
            ))

        } catch(e: Exception) {
            try {
                val localResp = repo.getLocalCalculatorList()
                val vmLocal = ConvertListLocal(localResp)
                Log.e("dari local db",vmLocal.toString())
                emit(Resource.Success<List<CalcListModel>>(vmLocal))

            }finally {
                println("Error: ${e.message}")
                emit(Resource.Error<List<CalcListModel>>(
                    message = "Terjadi kesalahan. Periksa koneksi anda"
                ))

            }

//            println("Error: ${e.message}")
//            emit(Resource.Error<List<CalcListModel>>(
//                message = "Terjadi kesalahan. Periksa koneksi anda"
//            ))

        }
    }

    private suspend fun updateLocalList(resp: List<GetCalcListResponse>){

        resp.forEach { element ->

            repo.addLocalCalculatorListOnly(
                FeedCalcLocalModel(
                    id = element.id,
                    feedCalc_name = element.feedcalc_name,
                    startAt = element.startAt,
                    species = element.species,
                    berat_tebar = element.berat_tebar,
                    dosis = element.dosis,
                    createdAt = element.createdAt,
                    updatedAt = element.updatedAt
                )
            )
        }
    }

    private fun ConvertListRespon(resp: List<GetCalcListResponse>  ): List<CalcListModel>{

        val vmData = mutableListOf<CalcListModel>()
        val currentCalendar = Calendar.getInstance()
        val currentTime = currentCalendar.timeInMillis
        Log.e("now",currentCalendar.toString())

        var startTime : Long
        var deltaTime : Long
        var leftDays: Int
        var hasDone: Boolean
        var hasStarted: Boolean

        resp.forEach { element ->

            startTime = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).parse(element.startAt).time
            if (startTime > currentTime){
                Log.e("status","belom mulai")
                deltaTime = startTime - currentTime
                hasStarted = false
                hasDone = false
            }else{
                Log.e("status","udah mulai")
                deltaTime = currentTime - startTime
                hasStarted = true

            }
            leftDays = TimeUnit.MILLISECONDS.toDays(deltaTime).toInt()
            if(leftDays>CULT_DAYS)
                hasDone = true
            else{
                hasDone = false
            }
            Log.e("selisih",leftDays.toString())

            vmData.add(
                CalcListModel(
                    feedcalcName = element.feedcalc_name,
                    calcId = element.id,
                    isDone = hasDone,
                    isStarted = hasStarted,
                    deltaDay = leftDays,

                )
            )
        }

        return vmData

    }

    private fun ConvertListLocal(resp: List<FeedCalcs>  ): List<CalcListModel>{

        val vmData = mutableListOf<CalcListModel>()
        val currentCalendar = Calendar.getInstance()
        val currentTime = currentCalendar.timeInMillis
        Log.e("now",currentCalendar.toString())

        var startTime : Long
        var deltaTime : Long
        var leftDays: Int
        var hasDone: Boolean
        var hasStarted: Boolean

        resp.forEach { element ->

            startTime = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).parse(element.startAt).time
            if (startTime > currentTime){
                Log.e("status","belom mulai")
                deltaTime = startTime - currentTime
                hasStarted = false
                hasDone = false
            }else{
                Log.e("status","udah mulai")
                deltaTime = currentTime - startTime
                hasStarted = true

            }
            leftDays = TimeUnit.MILLISECONDS.toDays(deltaTime).toInt()
            if(leftDays>CULT_DAYS)
                hasDone = true
            else{
                hasDone = false
            }
            Log.e("selisih",leftDays.toString())

            vmData.add(
                CalcListModel(
                    feedcalcName = element.feedCalc_name!!,
                    calcId = element.id,
                    isDone = hasDone,
                    isStarted = hasStarted,
                    deltaDay = leftDays,

                    )
            )
        }

        return vmData

    }
}