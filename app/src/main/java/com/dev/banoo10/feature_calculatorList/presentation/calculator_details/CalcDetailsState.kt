package com.dev.banoo10.feature_calculatorList.presentation.calculator_details

import com.dev.banoo10.feature_calculatorList.domain.model.CalcDetailsRecordModel

data class CalcDetailsState(
    val isLoading: Boolean = false,
    val name: String = "",
    val calendarList: List<String> = emptyList(),
    val startAt: String = "",
    val endAt: String = "",
    val feedData: List<CalcDetailsRecordModel> = emptyList(),
    val todayCalendarPage: Int = -1,
    val todayDate: Int ?= null,
    val index: Int = -1
)
