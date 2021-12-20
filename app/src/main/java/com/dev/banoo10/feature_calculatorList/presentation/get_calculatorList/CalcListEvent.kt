package com.dev.banoo10.feature_calculatorList.presentation.get_calculatorList


sealed class CalcListEvent{
//    data class EnteredOTP(val value:String): CalcListEvent()
    object AddCalc: CalcListEvent()
//    object SendPhone: CalcListEvent()
}
