package com.dev.banoo10.feature_personalForm.data.repository

import com.dev.banoo10.Database
import com.dev.banoo10.core.constants.HttpRoutes
import com.dev.banoo10.feature_auth.domain.model.UserModel
import com.dev.banoo10.feature_personalForm.data.remote.dto.PersonalDataRequest
import com.dev.banoo10.feature_personalForm.data.remote.dto.PersonalDataResponse
import com.dev.banoo10.feature_personalForm.domain.repository.PersonalDataRepo
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class PersonalDataRepoImpl @Inject constructor(
    private val client: HttpClient,
    private val database: Database
):PersonalDataRepo {
    override fun getAccessToken(): String {
        return database.userQueryQueries.selectAccToken().executeAsOne()
    }

    override suspend fun postPersonalData(
        personalDataRequest: PersonalDataRequest,
        accToken: String
    ): PersonalDataResponse {
        return client.post<PersonalDataResponse> {
            url(HttpRoutes.REQ_EDIT_PROFILE_URL)
            headers{
                append(HttpHeaders.Authorization, "Bearer $accToken")
            }
            contentType(ContentType.Application.Json)
            body = personalDataRequest
        }
    }
}