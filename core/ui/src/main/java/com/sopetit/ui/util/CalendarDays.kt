package com.sopetit.ui.util

import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth

fun generateCalendarDays(
    year: Int,
    month: Int,
): List<LocalDate> {
    val yearMonth = YearMonth.of(year, month)
    val lastDayOfMonth = yearMonth.atEndOfMonth()

    val days = mutableListOf<LocalDate>()

    for (day in 1..lastDayOfMonth.dayOfMonth) {
        days.add(yearMonth.atDay(day))
    }

    return days
}

fun setBeforeYearMonth(
    currentYear: Int,
    currentMonth: Int,
): List<Int> {
    val essentialYear: Int
    val essentialMonth: Int
    if (currentMonth == 1) {
        essentialYear = currentYear - 1
        essentialMonth = 12
    } else {
        essentialYear = currentYear
        essentialMonth = currentMonth - 1
    }

    return listOf(essentialYear, essentialMonth)
}

fun setAfterYearMonth(
    currentYear: Int,
    currentMonth: Int,
): List<Int> {
    val essentialYear: Int
    val essentialMonth: Int
    if (currentMonth == 12) {
        essentialYear = currentYear + 1
        essentialMonth = 1
    } else {
        essentialYear = currentYear
        essentialMonth = currentMonth + 1
    }

    return listOf(essentialYear, essentialMonth)
}
