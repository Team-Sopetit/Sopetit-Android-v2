package com.sopetit.achieve.calendar

import com.sopetit.domain.entity.response.calendar.CalendarModel
import com.sopetit.ui.base.PageState
import org.threeten.bp.LocalDate

data class CalendarPageState (
    val selectedDate: LocalDate = LocalDate.now(),
    val calendarList: Map<String, CalendarModel> = emptyMap()
): PageState