package com.dev.banoo10.feature_calculatorList.data.repository

import com.dev.banoo10.Database
import com.dev.banoo10.FeedCalcs
import com.dev.banoo10.core.Resource
import com.dev.banoo10.core.constants.HttpRoutes
import com.dev.banoo10.feature_calculatorList.data.remote.dto.add_calculator.AddCalcRequest
import com.dev.banoo10.feature_calculatorList.data.remote.dto.add_calculator.AddCalcResponse
import com.dev.banoo10.feature_calculatorList.data.remote.dto.delete_calculator.DeleteCalcResponse
import com.dev.banoo10.feature_calculatorList.data.remote.dto.get_calculatorList.GetCalcListResponse
import com.dev.banoo10.feature_calculatorList.domain.model.FeedCalcLocalModel
import com.dev.banoo10.feature_calculatorList.domain.model.SchedCalcLocalModel
import com.dev.banoo10.feature_calculatorList.domain.repository.CalculatorRepo
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CalculatorRepoImpl @Inject constructor(
    private val client: HttpClient,
    private val database: Database
): CalculatorRepo {

    override fun getAccessToken(): String {
        return database.userQueryQueries.selectAccToken().executeAsOne()
    }

    override suspend fun postAddCalculator(
        addCalcRequest: AddCalcRequest,
        accToken: String): AddCalcResponse {
        return client.post<AddCalcResponse>{
            url(HttpRoutes.ADD_CALCULATOR_URL)
            headers{
                append(HttpHeaders.Authorization, "Bearer $accToken")
            }
            contentType(ContentType.Application.Json)
            body = addCalcRequest

        }

    }

    override suspend fun addLocalCalculator(
        feedCalcLocalModel: FeedCalcLocalModel,
        schedCalcLocalModel: ArrayList<SchedCalcLocalModel>
    ) {
        database.userQueryQueries.addFeedCalc(
            feedCalcLocalModel.id,
            feedCalcLocalModel.feedCalc_name,
            feedCalcLocalModel.startAt,
            feedCalcLocalModel.berat_tebar,
            feedCalcLocalModel.dosis,
            feedCalcLocalModel.createdAt,
            feedCalcLocalModel.updatedAt
        )

        schedCalcLocalModel.forEach { element ->
            database.userQueryQueries.addFeedCalcShed(
                element.id,
                element.day,
                element.pakan_harian,
                element.penyerapan,
                element.berat_akhir,
                element.feedCalcId
            )
        }
//        database.userQueryQueries.addFeedCalcShed(
//            schedCalcLocalModel.id,
//            schedCalcLocalModel.day,
//            schedCalcLocalModel.pakan_harian,
//            schedCalcLocalModel.penyerapan,
//            schedCalcLocalModel.berat_akhir,
//            schedCalcLocalModel.feedCalcId
//        )
    }

    override suspend fun getCalculatorList(accToken: String): List<GetCalcListResponse> {
         return client.get{
             url(HttpRoutes.GET_CALCULATOR_URL)
             headers {
                 append(HttpHeaders.Authorization, "Bearer $accToken")
             }
         }
    }

    override suspend fun getLocalCalculatorList(): List<FeedCalcs> {
        return database.userQueryQueries.selectAllFeedCalcs().executeAsList()
    }

    override suspend fun getDeleteCalculatorbyId(accToken: String, id: String): DeleteCalcResponse {
        return client.get<DeleteCalcResponse> {
            url(HttpRoutes.DELETE_CALCULATOR_URL + "/$id")
            headers {
                append(HttpHeaders.Authorization, "Bearer $accToken")
            }
        }
    }

    override suspend fun deleteLocalCalculator(id: String) {
        database.userQueryQueries.deleteFeedCalcById(id)
    }

    override suspend fun addLocalCalculatorListOnly(feedCalcLocalModel: FeedCalcLocalModel) {
        database.userQueryQueries.addFeedCalc(
            feedCalcLocalModel.id,
            feedCalcLocalModel.feedCalc_name,
            feedCalcLocalModel.startAt,
            feedCalcLocalModel.berat_tebar,
            feedCalcLocalModel.dosis,
            feedCalcLocalModel.createdAt,
            feedCalcLocalModel.updatedAt
        )
    }
}