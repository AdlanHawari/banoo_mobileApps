package com.dev.banoo10.feature_calculatorList.data.remote.dto.get_calculatorList

import kotlinx.serialization.Serializable

@Serializable
data class GetCalcListResponse(
    val id: Int,
    val feedcalc_name: String,
    val startAt: String,
    val berat_tebar: Float,
    val dosis: Float,
    val species: String,
    val createdAt: String,
    val updatedAt: String,
    val userId: String,
    val uuid: String
)
