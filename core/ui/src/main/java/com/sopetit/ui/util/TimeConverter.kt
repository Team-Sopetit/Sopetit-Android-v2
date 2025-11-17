package com.sopetit.ui.util

import android.annotation.SuppressLint
import com.sopetit.designsystem.Empty
import com.sopetit.designsystem.Time24Format
import com.sopetit.designsystem.TimeAM
import com.sopetit.designsystem.TimeAmPmFormat
import com.sopetit.designsystem.TimeDefault
import com.sopetit.designsystem.TimePM

@SuppressLint("DefaultLocale")
fun convertTo24HourFormat(
    hour: Int,
    minuteStr: String,
    amPm: String,
): String {
    if (minuteStr.isEmpty()) {
        return TimeDefault
    } else {
        val minute = minuteStr.toInt()

        val hour24 =
            when (amPm) {
                TimeAM -> if (hour == 12) 0 else hour
                TimePM -> if (hour == 12) 12 else hour + 12
                else -> Empty
            }

        return String.format(Time24Format, hour24, minute)
    }
}

@SuppressLint("DefaultLocale")
fun convertToAmPmFormat(time24: String): String {
    val parts = time24.split(":")
    if (parts.size < 2) return Empty

    val hour = parts[0].toIntOrNull() ?: return Empty
    val minute = parts[1].toIntOrNull() ?: return Empty

    val amPm = if (hour < 12) TimeAM else TimePM
    val hour12 =
        when {
            hour == 0 -> 12
            hour > 12 -> hour - 12
            else -> hour
        }

    return String.format(TimeAmPmFormat, amPm, hour12, minute)
}
