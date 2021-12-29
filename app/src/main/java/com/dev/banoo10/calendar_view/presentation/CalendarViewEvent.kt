package com.dev.banoo10.calendar_view.presentation

sealed class CalendarViewEvent {
//    data class StartDate(val value: String): CalendarViewEvent()
    data class CreateCalendar(val value: String): CalendarViewEvent()
}