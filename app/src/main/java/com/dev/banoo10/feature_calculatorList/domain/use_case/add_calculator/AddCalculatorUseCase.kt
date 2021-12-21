package com.dev.banoo10.feature_calculatorList.domain.use_case.add_calculator

import android.util.Log
import com.dev.banoo10.core.Resource
import com.dev.banoo10.feature_calculatorList.data.remote.dto.add_calculator.AddCalcRequest
import com.dev.banoo10.feature_calculatorList.data.remote.dto.add_calculator.AddCalcResponse
import com.dev.banoo10.feature_calculatorList.domain.model.CalcElement
import com.dev.banoo10.feature_calculatorList.domain.model.FeedCalcLocalModel
import com.dev.banoo10.feature_calculatorList.domain.model.NewCalculationModel
import com.dev.banoo10.feature_calculatorList.domain.model.SchedCalcLocalModel
import com.dev.banoo10.feature_calculatorList.domain.repository.CalculatorRepo
import com.dev.banoo10.feature_personalForm.data.remote.dto.PersonalDataResponse
import io.ktor.client.features.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.ArrayList
import javax.inject.Inject

class AddCalculatorUseCase @Inject constructor(
    private val repo: CalculatorRepo

) {

    suspend operator fun invoke(
        newCalculationModel: NewCalculationModel
    ):Flow<Resource<AddCalcResponse>> = flow{
        Log.e("input", newCalculationModel.toString())

        val persenAbsorb = 0.7f

        val arrayData = ArrayList<CalcElement>(90)
//        Log.e("panjang",arrayData.)
        var a:Float = newCalculationModel.dosis * newCalculationModel.berat_tebar
        var b:Float = persenAbsorb * a
        var c:Float =  newCalculationModel.berat_tebar + b

        (0 until 90).forEach{ index ->
//            Log.e("ini", index.toString())
            if (index>0){
                a = newCalculationModel.dosis * arrayData[index - 1].berat_akhir
                b = persenAbsorb * a
                c = arrayData[index - 1].berat_akhir + b
            }
            arrayData.add(CalcElement(
                day = index + 1,
                pakan_harian = String.format("%.1f", a).toFloat(),
                penyerapan = String.format("%.1f", b).toFloat(),
                berat_akhir = String.format("%.1f", c).toFloat()
            ))
//        Log.e("data $index", arrayData[index].toString())

        }

        try {
            emit(Resource.Loading<AddCalcResponse>())
            val accToken = repo.getAccessToken()

            Log.e("token",accToken)

            val resp = repo.postAddCalculator(
                AddCalcRequest(
                    startAt = newCalculationModel.startAt,
                    feedcalc_name = newCalculationModel.feedcalc_name,
                    species = newCalculationModel.species,
                    berat_tebar = newCalculationModel.berat_tebar,
                    dosis= newCalculationModel.dosis,
                    data = arrayData
                ),
                accToken
            )

            val dataStored = ArrayList<SchedCalcLocalModel>()
            resp.record.forEach { element ->
                dataStored.add(
                    SchedCalcLocalModel(
                        id = element.id,
                        day = element.day,
                        pakan_harian = element.pakan_harian,
                        penyerapan = element.penyerapan,
                        berat_akhir = element.berat_akhir,
                        feedCalcId = element.feedCalcId
                    )
                )
            }


            //store to sqldelight
//            repo.addLocalCalculator(
//                FeedCalcLocalModel(
//                    id = resp.id,
//                    feedCalc_name = resp.feedcalc_name,
//                    startAt = resp.startAt,
//                    berat_tebar = resp.berat_tebar,
//                    dosis = resp.dosis,
//                    createdAt = resp.createdAt,
//                    updatedAt = resp.updatedAt
//                ),
//                dataStored
//            )

            emit(Resource.Success<AddCalcResponse>(resp))
            Log.e("use_case",resp.record.size.toString())

        }catch(e: RedirectResponseException) {
            // 3xx - responses
            Log.e("usecase",e.response.status.description)
            emit(Resource.Error<AddCalcResponse>(""))
//            println("Error: ${e.response.status.description}")
        } catch(e: ClientRequestException) {
            // 4xx - responses
//            println("Error: ${e.response.status.description}")
            Log.e("usecase",e.response.status.description)
            emit(Resource.Error<AddCalcResponse>("Client Error"))
            null
        } catch(e: ServerResponseException) {
            // 5xx - responses
//            println("Error: ${e.response.status.description}")
            Log.e("usecase",e.response.status.description)
            emit(Resource.Error<AddCalcResponse>("Server Error"))
//            null
        } catch(e: Exception) {
//            println("Error: ${e.message}")
            Log.e("huhuhu",e.localizedMessage)
            emit(Resource.Error<AddCalcResponse>("Terjadi kesalahan. Periksa koneksi anda"))
//            null
        }
    }
}