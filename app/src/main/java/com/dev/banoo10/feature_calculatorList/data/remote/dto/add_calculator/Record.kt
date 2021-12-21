package com.dev.banoo10.feature_calculatorList.data.remote.dto.add_calculator

import com.dev.banoo10.feature_calculatorList.util.DateAsLongSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Record(
    val id: Int,
    val day: Int,
    val pakan_harian: Float,
    val penyerapan: Float,
    val berat_akhir: Float,
    val feedCalcId: String,
    val createdAt: String,
    val updatedAt: String,

    )
