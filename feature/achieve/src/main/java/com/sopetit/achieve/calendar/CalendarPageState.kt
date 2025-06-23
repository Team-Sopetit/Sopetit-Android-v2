package com.sopetit.achieve.calendar

import com.sopetit.ui.base.PageState
import org.threeten.bp.LocalDate

data class CalendarPageState (
    val selectedDate: LocalDate = LocalDate.now()
): PageState