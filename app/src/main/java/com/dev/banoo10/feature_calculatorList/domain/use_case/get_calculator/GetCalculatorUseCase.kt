package com.dev.banoo10.feature_calculatorList.domain.use_case.get_calculator

import android.util.Log
import com.dev.banoo10.FeedCalcs
import com.dev.banoo10.core.Resource
import com.dev.banoo10.core.constants.Constants.CULT_DAYS
import com.dev.banoo10.core.constants.DatePattern
import com.dev.banoo10.core.constants.HttpRoutes
import com.dev.banoo10.feature_calculatorList.data.remote.dto.get_calculatorList.GetCalcListResponse
import com.dev.banoo10.feature_calculatorList.domain.model.CalcListModel
import com.dev.banoo10.feature_calculatorList.domain.model.FeedCalcLocalModel
import com.dev.banoo10.feature_calculatorList.domain.model.SchedCalcLocalModel
import com.dev.banoo10.feature_calculatorList.domain.model.UpdateDBResult
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
            val updatedDB = updateLocalList(resp, repo)
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
            Log.e("error msg", e.localizedMessage)
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

    private suspend fun updateLocalList(resp: List<GetCalcListResponse> ,repo: CalculatorRepo): UpdateDBResult{

        val result = UpdateDBResult()

        if(resp.isEmpty()){
            Log.e("isi resp","kosong")
            repo.deleteLocalCalculatorList()
            repo.deleteLocalSchedCalculator()
        }else{
            val respLocal = repo.getLocalCalculatorList()

            if (respLocal.isEmpty()){

                resp.forEach { element ->
                    repo.addLocalCalculatorListOnly(
                        FeedCalcs(
                            id = element.id,
                            feedCalc_name = element.feedcalc_name,
                            startAt = element.startAt,
                            species = element.species,
                            berat_tebar = element.berat_tebar,
                            dosis = element.dosis,
                            createdAt = element.createdAt,
                            updatedAt = element.updatedAt
                        )
//                        FeedCalcLocalModel(
//                            id = element.id,
//                            feedCalc_name = element.feedcalc_name,
//                            startAt = element.startAt,
//                            species = element.species,
//                            berat_tebar = element.berat_tebar,
//                            dosis = element.dosis,
//                            createdAt = element.createdAt,
//                            updatedAt = element.updatedAt
//                        )
                    )
                }

            }
            else{

                //start of deleting unexisted remote data in db block
                //filter local db data which is not exist in remote db
                fun List<FeedCalcs>.f(fooApiList: List<GetCalcListResponse>) = filterNot { m -> fooApiList.any { it.id == m.id } }
                val unExistedRemote = respLocal.f(resp)
//                Log.e("filtered",unExistedRemote.toString())

                unExistedRemote.forEach { element ->
                    repo.deleteLocalCalculator(element.id)
                }
                //end of deleting unexisted remote data in db block

                //start of adding unexisted remote data to db block
                fun List<GetCalcListResponse>.g(fooApiList: List<FeedCalcs>) = filterNot { m -> fooApiList.any { it.id == m.id } }
                val unExistedLocal = resp.g(respLocal)
//                Log.e("filtered",unExistedLocal.toString())

                unExistedLocal.forEach { element ->
                    repo.addLocalCalculatorListOnly(
                        FeedCalcs(
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

                //end of adding unexisted remote data to db block


            }

        }
        return result

//        resp.forEach { element ->
//
//            repo.addLocalCalculatorListOnly(
//                FeedCalcLocalModel(
//                    id = element.id,
//                    feedCalc_name = element.feedcalc_name,
//                    startAt = element.startAt,
//                    species = element.species,
//                    berat_tebar = element.berat_tebar,
//                    dosis = element.dosis,
//                    createdAt = element.createdAt,
//                    updatedAt = element.updatedAt
//                )
//            )
//        }
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

//            startTime = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).parse(element.startAt).time
            startTime = SimpleDateFormat(DatePattern.DATEONLY, Locale.getDefault()).parse(element.startAt).time
            if (startTime > currentTime){
                Log.e("status","belom mulai")
                deltaTime = startTime - currentTime
                leftDays = TimeUnit.MILLISECONDS.toDays(deltaTime).toInt() + 1
                hasStarted = false
                hasDone = false
            }else{
                Log.e("status","udah mulai")
                deltaTime = currentTime - startTime
                leftDays = TimeUnit.MILLISECONDS.toDays(deltaTime).toInt() + 1
                hasStarted = true
                if(leftDays>CULT_DAYS){
                    hasDone = true
                }
                else{
                    hasDone = false
                }
            }
//            Log.e("real time",TimeUnit.MILLISECONDS.toDays(deltaTime).javaClass.simpleName)
//            leftDays = TimeUnit.MILLISECONDS.toDays(deltaTime).toInt()
//            if(leftDays>CULT_DAYS){
//                hasDone = true
//            }
//            else{
//                hasDone = false
//            }
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

//            startTime = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).parse(element.startAt).time
            startTime = SimpleDateFormat(DatePattern.DATEONLY, Locale.getDefault()).parse(element.startAt).time
            if (startTime > currentTime){
                Log.e("status","belom mulai")
                deltaTime = startTime - currentTime
                leftDays = TimeUnit.MILLISECONDS.toDays(deltaTime).toInt() + 1
                hasStarted = false
                hasDone = false
            }else{
                Log.e("status","udah mulai")
                deltaTime = currentTime - startTime
                leftDays = TimeUnit.MILLISECONDS.toDays(deltaTime).toInt() + 1
                hasStarted = true
                if(leftDays>CULT_DAYS){
                    hasDone = true
                }
                else{
                    hasDone = false
                }
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