package com.sopetit.ui.common.type

import com.sopetit.design_system.TimeAM
import com.sopetit.design_system.TimeMinuteFifty
import com.sopetit.design_system.TimeMinuteForty
import com.sopetit.design_system.TimeMinuteHalf
import com.sopetit.design_system.TimeMinuteTen
import com.sopetit.design_system.TimeMinuteTwenty
import com.sopetit.design_system.TimeMinuteZero
import com.sopetit.design_system.TimePM

enum class TimeMinuteType(
    val id: Int,
    val data: String
) {
    ZERO(0, TimeMinuteZero),
    ONE(1, TimeMinuteTen),
    TWO(2, TimeMinuteTwenty),
    HALF(3, TimeMinuteHalf),
    FOURTH(4, TimeMinuteForty),
    FIFTH(5, TimeMinuteFifty);

    companion object {
        fun getMinuteData(id: Int) = entries.firstOrNull { it.id == id }?.data ?: ""
    }
}

enum class TimeDayType(
    val id: Int,
    val data: String
) {
    AM(0, TimeAM),
    PM(1, TimePM);

    companion object {
        fun getDayData(id: Int) = TimeDayType.entries.firstOrNull { it.id == id }?.data ?: ""
    }
}