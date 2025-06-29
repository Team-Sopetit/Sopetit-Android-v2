package com.sopetit.achieve.calendar

import androidx.lifecycle.viewModelScope
import com.sopetit.domain.entity.request.calendar.CalendarRequestModel
import com.sopetit.domain.entity.response.calendar.CalendarModel
import com.sopetit.domain.usecase.calendar.GetCalendarUseCase
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val getCalendarUseCase: GetCalendarUseCase,
) : BaseViewModel<CalendarPageState>(
    CalendarPageState()
) {

    init {
        initGetCalendarList(LocalDate.now())
    }

    private fun initGetCalendarList(yearMonth: LocalDate) {
        viewModelScope.launch {
            getCalendarUseCase(CalendarRequestModel(yearMonth.year, yearMonth.monthValue)).collect {
                resultResponse(it, ::onSuccessGetCalendar)
            }
        }
    }

    private fun onSuccessGetCalendar(data: Map<String, CalendarModel>) {
        updateState(
            uiState.value.copy(
                calendarList = data
            )
        )
    }

    fun selectDate(date: LocalDate) {
        updateState(
            uiState.value.copy(
                selectedDate = date
            )
        )
    }
}