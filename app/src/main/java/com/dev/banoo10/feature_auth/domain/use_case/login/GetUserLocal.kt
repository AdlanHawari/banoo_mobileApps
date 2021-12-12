package com.dev.banoo10.feature_auth.domain.use_case.login

import com.dev.banoo10.Database
import com.dev.banoo10.UserTable
import com.dev.banoo10.feature_auth.data.repository.AuthRepoImpl
import com.dev.banoo10.feature_auth.domain.repository.AuthRepo
import com.squareup.sqldelight.Query
import javax.inject.Inject

class GetUserLocal @Inject constructor(
    private val repo: AuthRepo
) {
    operator fun invoke(): List<UserTable>{
        return repo.getUserLocal()
    }

}