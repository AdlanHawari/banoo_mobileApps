package com.dev.banoo10.feature_auth.domain.use_case.login

import com.dev.banoo10.Database
import com.dev.banoo10.UserTable
import com.dev.banoo10.core.Resource
import com.dev.banoo10.feature_auth.domain.model.UserModel
import com.dev.banoo10.feature_auth.domain.repository.AuthRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddUserLocal @Inject constructor(
//    private val database: Database,
    private val repo: AuthRepo
) {

    suspend operator fun invoke(userModel: UserModel){
        repo.addUserLocal(userModel)
    }

}