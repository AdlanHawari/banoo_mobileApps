package com.dev.banoo10.feature_personalForm.domain.repository

import com.dev.banoo10.feature_auth.domain.model.UserModel
import com.dev.banoo10.feature_personalForm.data.remote.dto.PersonalDataRequest
import com.dev.banoo10.feature_personalForm.data.remote.dto.PersonalDataResponse

interface PersonalDataRepo {

    fun getAccessToken(): String

    suspend fun postPersonalData(personalDataRequest: PersonalDataRequest, accToken: String): PersonalDataResponse
}