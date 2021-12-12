package com.dev.banoo10.feature_auth.domain.use_case.login

import android.util.Log
import com.dev.banoo10.UserTable
import com.dev.banoo10.core.Resource
import com.dev.banoo10.feature_auth.domain.repository.AuthRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAccTokenLocal @Inject constructor(
    private val repo: AuthRepo
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
//    operator fun invoke(): Flow<Resource<SelectAccToken?>> = flow {
//        try{
//            emit(Resource.Loading<SelectAccToken?>())
//            val accTkn = repo.getAccessToken()
//            Log.e("use_case",accTkn.toString())
//            if(accTkn != null){
//                emit(Resource.Success<SelectAccToken?>(accTkn))
//            }else{
////                emit(Resource.Success<Int>())
//            }
//
//        }catch (e:Exception){
//            emit(Resource.Error<SelectAccToken?>(e.localizedMessage ?: "An unexpected error occured"))
//
//        }
//    }
}