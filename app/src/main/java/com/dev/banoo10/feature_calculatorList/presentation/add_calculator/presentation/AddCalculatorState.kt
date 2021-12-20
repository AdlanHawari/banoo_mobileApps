package com.dev.banoo10.feature_calculatorList.presentation.add_calculator.presentation

import android.content.Context

data class AddCalculatorState (
    val year: Int,
    val month: Int,
    val day: Int,
    val date: String = "",
    val isLoading: Boolean = false,
)