package com.dev.banoo10.feature_calculatorList.domain.use_case.get_details

import android.util.Log
import com.dev.banoo10.FeedCalcs
import com.dev.banoo10.core.Resource
import com.dev.banoo10.core.constants.Constants
import com.dev.banoo10.core.constants.DatePattern
import com.dev.banoo10.feature_calculatorList.domain.model.CalcDetailsModel
import com.dev.banoo10.feature_calculatorList.domain.model.CalcDetailsRecordModel
import com.dev.banoo10.feature_calculatorList.domain.model.CalcListModel
import com.dev.banoo10.feature_calculatorList.domain.repository.CalculatorRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(
    private val repo: CalculatorRepo
) {

    suspend operator fun invoke(id: String): Flow<Resource<CalcDetailsModel>> = flow {
        emit(Resource.Loading<CalcDetailsModel>())
//        emit(Resource.Error<CalcDetailsModel>(message = "mantab"))
//        emit(Resource.Error<CalcDetailsModel>(
//            message = "Client Error"
//        ))
        try {

            val resp = repo.getLocalCalculatorListById(id)
            val respDetails = repo.getLocalSchedCalculatorById(id)
            val calendarList = calendarCalc(resp.startAt!!, Constants.CULT_DAYS)
//            Log.e("start at", resp.startAt)
//            Log.e("end at", endAt)

            val dataDetails= mutableListOf<CalcDetailsRecordModel>()
            respDetails.forEachIndexed { index, element ->
                dataDetails.add(
                    CalcDetailsRecordModel(
                        id = element.id,
                        day = element.day?:0,
                        pakan_harian = element.pakan_harian?:0f,
                        penyerapan = element.penyerapan?:0f,
                        berat_akhir = element.berat_akhir?:0f,
                        feedCalcId = element.feedCalcId?:""
                    )
                )
            }

            val data = CalcDetailsModel(
                id = resp.id,
                feedCalc_name = resp.feedCalc_name?:"",
                startAt = resp.startAt,
                endAt = calendarList[calendarList.lastIndex],
                berat_tebar = resp.berat_tebar?:0f,
                dosis = resp.dosis?:0f,
                species = resp.species?:"",
                createdAt = resp.createdAt?:"",
                updatedAt = resp.updatedAt?:"",
                calendarList = calendarList,
                record = dataDetails,
            )

            emit(Resource.Success<CalcDetailsModel>(data))


        }catch (e:Exception){
            emit(Resource.Error<CalcDetailsModel>(message = e.toString()))
        }

    }

    private fun calendarCalc(startDateString: String, dayToGo: Int): List<String>{
//        val calendar = Calendar.getInstance()
        val calendarUsecase = Calendar.getInstance()
        val simpleDF = SimpleDateFormat(DatePattern.DATEONLY)
        val startDate = simpleDF.parse(startDateString)
        calendarUsecase.setTime(startDate)
        val startMonth = calendarUsecase.get(Calendar.MONTH)
        val startYear = calendarUsecase.get(Calendar.YEAR)

        //end date
        calendarUsecase.add(Calendar.DAY_OF_YEAR, dayToGo - 1)
        val endDateString = simpleDF.format(calendarUsecase.getTime())
        val endMonth = calendarUsecase.get(Calendar.MONTH)
        val endYear = calendarUsecase.get(Calendar.YEAR)
//        Log.e("endyear", endYear.toString())
        val monthLength: Int

        if (startMonth > endMonth){
            monthLength = endMonth - startMonth + 13
        }else{
            monthLength = endMonth - startMonth + 1
        }

//        Log.e("length",monthLength.toString())
        val calendarItem = mutableListOf<String>()
        var currentMonth = startMonth
        var inputDate = ""
        (0 until monthLength).forEach { index ->
            if (index.equals(0)){
//                calendarItem.add(startDateString)
                inputDate = startDateString
            }
            else if (index.equals(monthLength - 1)){
//                calendarItem.add(endDateString)
                inputDate = endDateString
            }
            else{
                calendarUsecase.set(startYear,startMonth+index,1)
                inputDate = simpleDF.format(calendarUsecase.getTime())
            }
            calendarItem.add(inputDate)
        }
        Log.e("item", calendarItem.toString())

        return calendarItem

    }
}