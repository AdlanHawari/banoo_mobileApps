package com.dev.banoo10.feature_calculatorList.domain.model

import java.util.*

data class NewCalculationModel (
    val name: String,
    val desc: String,
    val berat_tebar: Float,
    val dosis: Float,
    val tebar: Date
)