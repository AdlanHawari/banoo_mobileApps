package com.dev.banoo10.feature_calculatorList.domain.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

data class NewCalculationModel (
    val feedcalc_name: String,
    val berat_tebar: Float,
    val species: String,
    val dosis: Float,
    val startAt: Date
)