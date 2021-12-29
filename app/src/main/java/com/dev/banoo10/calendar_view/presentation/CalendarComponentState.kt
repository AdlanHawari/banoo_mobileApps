package com.dev.banoo10.calendar_view.presentation

data class CalendarComponentState(
    val monthName: String ="",
    val totalWeek: Int = 0,
    val currDate: Int = 0,
    val maxDay: Int = 0,
    val firstPos: DatePosition = DatePosition(
        day = 0,
        week = 0
    )
)