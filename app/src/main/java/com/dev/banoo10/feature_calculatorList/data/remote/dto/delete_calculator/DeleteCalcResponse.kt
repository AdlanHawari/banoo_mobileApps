package com.dev.banoo10.feature_calculatorList.data.remote.dto.delete_calculator

import kotlinx.serialization.Serializable

@Serializable
data class DeleteCalcResponse(
    val deleted: Int
)
