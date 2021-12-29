package com.dev.banoo10.feature_calculatorList.presentation.calculator_details

data class CalcDetailsState(
    val isLoading: Boolean = false,
    val name: String = "",
    val calendarList: List<String> = emptyList(),
    val startAt: String = "",
    val endAt: String = "",
)
