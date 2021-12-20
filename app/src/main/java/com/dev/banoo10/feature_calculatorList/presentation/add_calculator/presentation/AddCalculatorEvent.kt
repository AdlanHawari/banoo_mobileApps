package com.dev.banoo10.feature_calculatorList.presentation.add_calculator.presentation

sealed class AddCalculatorEvent {

    data class EnteredOTP(val value:String): AddCalculatorEvent()
    data class SetDateStart(val value:String): AddCalculatorEvent()
    object DatePickerPressed: AddCalculatorEvent()
    object CreateCalculation: AddCalculatorEvent()
    object SendPhone: AddCalculatorEvent()
}