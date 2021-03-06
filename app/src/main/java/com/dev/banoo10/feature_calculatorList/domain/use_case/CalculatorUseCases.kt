package com.dev.banoo10.feature_calculatorList.domain.use_case

import com.dev.banoo10.feature_calculatorList.domain.use_case.add_calculator.AddCalculatorUseCase
import com.dev.banoo10.feature_calculatorList.domain.use_case.delete_calculator.DeleteCalculatorUseCase
import com.dev.banoo10.feature_calculatorList.domain.use_case.get_calculator.GetCalculatorUseCase
import com.dev.banoo10.feature_calculatorList.domain.use_case.get_details.GetDetailsUseCase


data class CalculatorUseCases(
    val getAccTokenLocal: GetAccTokenCalc,
    val addCalculator: AddCalculatorUseCase,
    val getCalculator: GetCalculatorUseCase,
    val deleteCalculator: DeleteCalculatorUseCase,
    val getDetails: GetDetailsUseCase
)