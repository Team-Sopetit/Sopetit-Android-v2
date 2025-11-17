package com.sopetit.domain.entity.response.calendar

data class CalendarModel(
    val memoId: Int = 0,
    val memoContent: String = "",
    val histories: List<CalendarHistoryModel>,
)
