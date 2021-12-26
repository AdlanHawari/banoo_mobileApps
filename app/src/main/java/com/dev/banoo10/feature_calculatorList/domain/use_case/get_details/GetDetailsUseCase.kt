package com.dev.banoo10.feature_calculatorList.domain.use_case.get_details

import com.dev.banoo10.FeedCalcs
import com.dev.banoo10.core.Resource
import com.dev.banoo10.feature_calculatorList.domain.model.CalcDetailsModel
import com.dev.banoo10.feature_calculatorList.domain.repository.CalculatorRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(
    private val repo: CalculatorRepo
) {

    suspend operator fun invoke(id: String): Flow<Resource<CalcDetailsModel>> = flow {
        emit(Resource.Loading<CalcDetailsModel>())
        try {

            val resp = repo.getLocalCalculatorListById(id)

            val dataSend = CalcDetailsModel(
                name = resp.feedCalc_name?:"",
                startAt = resp.startAt?:"",

            )

            emit(Resource.Success<CalcDetailsModel>(dataSend))


        }catch (e:Exception){
            emit(Resource.Error<CalcDetailsModel>(message = "Terjadi kesalahan"))
        }

    }
}