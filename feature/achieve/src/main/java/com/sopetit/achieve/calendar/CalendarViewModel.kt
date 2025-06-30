package com.sopetit.achieve.calendar

import androidx.lifecycle.viewModelScope
import com.sopetit.domain.entity.enums.RoutineType
import com.sopetit.domain.entity.request.calendar.CalendarRequestModel
import com.sopetit.domain.entity.response.calendar.CalendarHistoryItemModel
import com.sopetit.domain.entity.response.calendar.CalendarModel
import com.sopetit.domain.entity.response.screen.RoutineDetailModel
import com.sopetit.domain.usecase.calendar.GetCalendarUseCase
import com.sopetit.domain.usecase.memberchallenge.DeleteMemberChallengeUseCase
import com.sopetit.domain.usecase.memberroutine.DeleteDailyRoutineHistoryUseCase
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val getCalendarUseCase: GetCalendarUseCase,
    private val deleteMemberChallengeUseCase: DeleteMemberChallengeUseCase,
    private val deleteDailyRoutineHistoryUseCase: DeleteDailyRoutineHistoryUseCase,
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

    fun setRoutineDetail(type: RoutineType, item: CalendarHistoryItemModel): RoutineDetailModel =
        RoutineDetailModel(
            routineId = item.historyId,
            routineType = type,
            content = item.content
        )

    fun deleteRoutine(routine: RoutineDetailModel) {
        when (routine.routineType) {
            RoutineType.Daily -> {
                deleteDailyRoutine(routine.routineId)
            }

            RoutineType.Challenge -> {
                deleteChallengeRoutine()
            }
        }
    }

    private fun deleteDailyRoutine(routineId: Int) {
        viewModelScope.launch {
            deleteDailyRoutineHistoryUseCase(routineId).collect {
                resultResponse(
                    it,
                    { initGetCalendarList(uiState.value.selectedDate) })
            }
        }
    }

    private fun deleteChallengeRoutine() {
        viewModelScope.launch {
            deleteMemberChallengeUseCase(Unit).collect {
                resultResponse(
                    it,
                    { initGetCalendarList(uiState.value.selectedDate) })
            }
        }
    }
}