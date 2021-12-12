package com.dev.banoo10.feature_auth.domain.use_case.login

import com.dev.banoo10.Database
import com.dev.banoo10.feature_auth.domain.repository.AuthRepo
import javax.inject.Inject

class DeleteUserLocal @Inject constructor(
    private val repo: AuthRepo
){
    suspend operator fun invoke(){
        repo.deleteUserLocal()
    }
}