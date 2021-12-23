package com.dev.banoo10.feature_calculatorList.domain.model

data class CalcListModel(
    val feedcalcName: String,
    val isDone: Boolean,
    val isStarted: Boolean,
    val deltaDay: Int
)
