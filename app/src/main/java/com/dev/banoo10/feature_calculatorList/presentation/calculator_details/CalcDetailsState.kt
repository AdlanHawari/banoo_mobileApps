package com.dev.banoo10.feature_calculatorList.presentation.calculator_details

import com.dev.banoo10.feature_calculatorList.domain.model.CalcDetailsModel

data class CalcDetailsState(
    val isLoading: Boolean = false,
    val isToday: Boolean = true,

    val name: String = "",
    val startAt: String = "",
    val endAt: String = "",
    val day: String = "",
    val pakan: String = "",
    val penyerapan: String = "",
    val beratAkhir: String = ""

)
