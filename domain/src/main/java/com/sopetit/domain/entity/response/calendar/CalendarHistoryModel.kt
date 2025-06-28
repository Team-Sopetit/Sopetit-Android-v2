package com.sopetit.domain.entity.response.calendar

data class CalendarHistoryModel(
    val themeId: Int = 0,
    val themeName: String = "",
    val histories: List<CalendarHistoryItemModel>,
)