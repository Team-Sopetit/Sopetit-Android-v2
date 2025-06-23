package com.sopetit.achieve.calendar

import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.threeten.bp.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(

): BaseViewModel<CalendarPageState>(
    CalendarPageState()
) {

    fun selectDate(date: LocalDate) {
        updateState(
            uiState.value.copy(
                selectedDate = date
            )
        )
    }
}