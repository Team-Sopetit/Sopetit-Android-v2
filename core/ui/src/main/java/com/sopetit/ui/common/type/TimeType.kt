package com.sopetit.ui.common.type

import com.sopetit.design_system.TimeAM
import com.sopetit.design_system.TimeMinuteHalf
import com.sopetit.design_system.TimeMinuteZero
import com.sopetit.design_system.TimePM

enum class TimeMinuteType(
    val id: Int,
    val data: String
) {
    ZERO(0, TimeMinuteZero),
    HALF(1, TimeMinuteHalf);

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