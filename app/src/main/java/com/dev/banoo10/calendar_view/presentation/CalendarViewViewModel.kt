package com.dev.banoo10.calendar_view.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*

//@HiltViewModel
class CalendarViewViewModel (

):ViewModel(){

    private val _calendarState = mutableStateOf(CalendarViewState())
    val calendarState: State<CalendarViewState> = _calendarState


    init {
        val now = Calendar.getInstance()
//        now.set(2021,7,28)
        val currDate = now.get(Calendar.DAY_OF_MONTH)

        val currPos = DatePosition(
            day = now.get(Calendar.DAY_OF_WEEK),
            week = now.get(Calendar.WEEK_OF_MONTH)
        )
        Log.e("currentPos",currPos.toString())

        //number of week in month
        val totalWeek = now.getActualMaximum(Calendar.WEEK_OF_MONTH)
        Log.e("totalweek", totalWeek.toString())

        //number of days in month
        val maxDay = now.getActualMaximum(Calendar.DAY_OF_MONTH)
        Log.e("maxDay", maxDay.toString())

        //first day position
        now.set(Calendar.DAY_OF_MONTH, 1)
        val firstPos = DatePosition(
            day = now.get(Calendar.DAY_OF_WEEK),
            week = now.get(Calendar.WEEK_OF_MONTH)
        )
        Log.e("firstPos",firstPos.toString())
        _calendarState.value = calendarState.value.copy(
            currDate = currDate,
            totalWeek = totalWeek,
            maxDay = maxDay,
            firstPos = firstPos,
            currentPos = currPos
        )

    }
}