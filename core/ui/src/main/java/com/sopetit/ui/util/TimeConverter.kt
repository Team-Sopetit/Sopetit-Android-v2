package com.sopetit.ui.util

import android.annotation.SuppressLint
import com.sopetit.design_system.TimeAM
import com.sopetit.design_system.TimePM

@SuppressLint("DefaultLocale")
fun convertTo24HourFormat(hour: Int, minuteStr: String, amPm: String): String {
    val minute = minuteStr.toInt()

    val hour24 = when (amPm) {
        TimeAM -> if (hour == 12) 0 else hour
        TimePM -> if (hour == 12) 12 else hour + 12
        else -> ""
    }

    return String.format("%02d:%02d:00", hour24, minute)
}
