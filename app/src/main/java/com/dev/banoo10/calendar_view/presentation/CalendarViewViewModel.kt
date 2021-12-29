package com.dev.banoo10.calendar_view.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dev.banoo10.core.constants.DatePattern
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.*

//@HiltViewModel
class CalendarViewViewModel (

):ViewModel(){

    private val _calendarState = mutableStateOf(CalendarViewState())
    val calendarState: State<CalendarViewState> = _calendarState

    val calendar = Calendar.getInstance()
    val simpleDF = SimpleDateFormat(DatePattern.DATEONLY)
    val titleDF = SimpleDateFormat(DatePattern.DATEYEAR, Locale.getDefault())

//    init {
//        val now = Calendar.getInstance()
//
//        val ari = simpleDF.parse("2022-01-31")
////        Log.e("now type",now.javaClass.simpleName)
////        Log.e("ari type",ari.javaClass.simpleName)
////        now.set(2021,7,28)
//        now.setTime(ari)
//        val currDate = now.get(Calendar.DAY_OF_MONTH)
//
//        val currPos = DatePosition(
//            day = now.get(Calendar.DAY_OF_WEEK),
//            week = now.get(Calendar.WEEK_OF_MONTH)
//        )
//        Log.e("currentPos",currPos.toString())
//
//        //number of week in month
//        val totalWeek = now.getActualMaximum(Calendar.WEEK_OF_MONTH)
//        Log.e("totalweek", totalWeek.toString())
//
//        //number of days in month
//        val maxDay = now.getActualMaximum(Calendar.DAY_OF_MONTH)
//        Log.e("maxDay", maxDay.toString())
//
//        //first day position
//        now.set(Calendar.DAY_OF_MONTH, 1)
//        val firstPos = DatePosition(
//            day = now.get(Calendar.DAY_OF_WEEK),
//            week = now.get(Calendar.WEEK_OF_MONTH)
//        )
//        Log.e("firstPos",firstPos.toString())
//        _calendarState.value = calendarState.value.copy(
//            currDate = currDate,
//            totalWeek = totalWeek,
//            maxDay = maxDay,
//            firstPos = firstPos,
//            currentPos = currPos
//        )
//
//    }

    fun onEvent(event: CalendarViewEvent){
        when(event){
            is CalendarViewEvent.CreateCalendar -> {
                val currentCalendar = simpleDF.parse(event.value)


                calendar.setTime(currentCalendar)
                val monthName = titleDF.format(calendar.getTime())
//                Log.e("month", monthName)
                val totalWeek = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH)
                val maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

                calendar.set(Calendar.DAY_OF_MONTH, 1)
                val firstDay = calendar.get(Calendar.DAY_OF_WEEK)
                val firstDayWeek = calendar.get(Calendar.WEEK_OF_MONTH)

                _calendarState.value = calendarState.value.copy(
//                    currDate = calendar.get()
                    month = monthName,
                    totalWeek = totalWeek,
                    maxDay = maxDay,
                    firstPos = calendarState.value.firstPos.copy(
                        day = firstDay,
                        week = firstDayWeek
                    )

                )
            }
        }

    }
}