package com.dev.banoo10.feature_auth.domain.model

import java.util.concurrent.TimeUnit

object TimerUtility {

    //convert time to milli seconds
    private const val TIME_FORMAT = "%02d:%02d"
    fun Long.formatTime(): String = String.format(
        TIME_FORMAT,
        TimeUnit.MILLISECONDS.toMinutes(this),
        TimeUnit.MILLISECONDS.toSeconds(this) % 60
    )
}