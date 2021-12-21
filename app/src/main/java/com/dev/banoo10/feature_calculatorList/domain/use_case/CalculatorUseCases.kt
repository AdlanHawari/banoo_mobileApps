package com.dev.banoo10.feature_calculatorList.domain.use_case

import com.dev.banoo10.feature_calculatorList.domain.use_case.add_calculator.AddCalculatorUseCase
import com.dev.banoo10.feature_calculatorList.domain.use_case.get_calculator.GetCalculatorUseCase


data class CalculatorUseCases(
    val getAccTokenLocal: GetAccTokenCalc,
    val addCalculator: AddCalculatorUseCase,
    val getCalculator: GetCalculatorUseCase
)