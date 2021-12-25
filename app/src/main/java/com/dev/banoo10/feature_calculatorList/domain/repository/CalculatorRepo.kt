package com.dev.banoo10.feature_calculatorList.domain.repository

import com.dev.banoo10.FeedCalcs
import com.dev.banoo10.feature_calculatorList.data.remote.dto.add_calculator.AddCalcRequest
import com.dev.banoo10.feature_calculatorList.data.remote.dto.add_calculator.AddCalcResponse
import com.dev.banoo10.feature_calculatorList.data.remote.dto.delete_calculator.DeleteCalcResponse
import com.dev.banoo10.feature_calculatorList.data.remote.dto.get_calculatorList.GetCalcListResponse
import com.dev.banoo10.feature_calculatorList.domain.model.FeedCalcLocalModel
import com.dev.banoo10.feature_calculatorList.domain.model.SchedCalcLocalModel
import kotlinx.coroutines.flow.Flow

interface CalculatorRepo {

    fun getAccessToken(): String

    suspend fun addLocalCalculator( feedCalcLocalModel: FeedCalcLocalModel, schedCalcLocalModel: ArrayList<SchedCalcLocalModel>)

    suspend fun postAddCalculator( addCalcRequest: AddCalcRequest, accToken: String): AddCalcResponse

    suspend fun getCalculatorList( accToken: String ): List<GetCalcListResponse>

    suspend fun getDeleteCalculatorbyId( accToken: String , id: String): DeleteCalcResponse

    suspend fun deleteLocalCalculator(id: String)

    suspend fun getLocalCalculatorList(): List<FeedCalcs>

//    suspend fun addLocalCalculatorListOnly(feedCalcLocalModel: FeedCalcLocalModel)
    suspend fun addLocalCalculatorListOnly(feedCalcs: FeedCalcs)

    suspend fun deleteLocalCalculatorList()

    suspend fun deleteLocalSchedCalculator()


}