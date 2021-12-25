package com.dev.banoo10.feature_calculatorList.presentation.add_calculator

sealed class AddCalculatorEvent {

    data class EnteredNamaBudidaya(val value:String): AddCalculatorEvent()
    data class EnteredSpesies(val value:String): AddCalculatorEvent()
    data class EnteredBeratTebar(val value:String): AddCalculatorEvent()
    data class EnteredDosis(val value:String): AddCalculatorEvent()
    data class SetDateStart(val value:String): AddCalculatorEvent()
    object DatePickerPressed: AddCalculatorEvent()
    object CreateCalculation: AddCalculatorEvent()
}