package com.dev.banoo10.feature_calculatorList.domain.use_case

import com.dev.banoo10.core.Resource
import com.dev.banoo10.feature_calculatorList.domain.repository.CalculatorRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAccTokenCalc @Inject constructor(
    private val repo: CalculatorRepo
) {
    operator fun invoke(): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading<String>())
            val token = repo.getAccessToken()
            emit(Resource.Success<String>(token))

        }catch (e:Exception){
            emit(Resource.Error<String>(e.localizedMessage?:"An unexcpected error occured"))
        }
    }
}