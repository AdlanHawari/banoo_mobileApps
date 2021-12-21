package com.dev.banoo10.feature_calculatorList.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CalcElement(
    val day: Int,
    val pakan_harian: Float,
    val penyerapan: Float,
    val berat_akhir: Float
)
