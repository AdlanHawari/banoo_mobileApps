package com.dev.banoo10.feature_calculatorList.presentation.calculator_details

sealed class CalcDetailsEvent{
    data class DatePick(val value: String): CalcDetailsEvent()
}
