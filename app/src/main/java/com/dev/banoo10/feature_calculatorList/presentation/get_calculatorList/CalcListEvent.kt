package com.dev.banoo10.feature_calculatorList.presentation.get_calculatorList


sealed class CalcListEvent{
//    data class EnteredOTP(val value:String): CalcListEvent()
    object AddCalc: CalcListEvent()
    object DeleteCalc: CalcListEvent()
    data class DeleteSelectedCalc(val value:String): CalcListEvent()
    object CancelDeleteCalc: CalcListEvent()
    object ToAccountEvent: CalcListEvent()
//    object SendPhone: CalcListEvent()
}
