package com.sopetit.ui.common.type

import com.sopetit.designsystem.TimeAM
import com.sopetit.designsystem.TimeMinuteFifty
import com.sopetit.designsystem.TimeMinuteForty
import com.sopetit.designsystem.TimeMinuteHalf
import com.sopetit.designsystem.TimeMinuteTen
import com.sopetit.designsystem.TimeMinuteTwenty
import com.sopetit.designsystem.TimeMinuteZero
import com.sopetit.designsystem.TimePM

enum class TimeMinuteType(
    val id: Int,
    val data: String,
) {
    ZERO(0, TimeMinuteZero),
    ONE(1, TimeMinuteTen),
    TWO(2, TimeMinuteTwenty),
    HALF(3, TimeMinuteHalf),
    FOURTH(4, TimeMinuteForty),
    FIFTH(5, TimeMinuteFifty),
    ;

    companion object {
        fun getMinuteData(id: Int) = entries.firstOrNull { it.id == id }?.data ?: ""
    }
}

enum class TimeDayType(
    val id: Int,
    val data: String,
) {
    AM(0, TimeAM),
    PM(1, TimePM),
    ;

    companion object {
        fun getDayData(id: Int) = TimeDayType.entries.firstOrNull { it.id == id }?.data ?: ""
    }
}
