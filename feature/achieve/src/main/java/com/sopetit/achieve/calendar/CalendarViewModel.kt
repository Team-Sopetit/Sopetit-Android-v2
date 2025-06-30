package com.sopetit.achieve.calendar

import androidx.lifecycle.viewModelScope
import com.sopetit.domain.entity.enums.RoutineType
import com.sopetit.domain.entity.request.calendar.CalendarRequestModel
import com.sopetit.domain.entity.request.memo.MemoWriteRequestModel
import com.sopetit.domain.entity.response.calendar.CalendarHistoryItemModel
import com.sopetit.domain.entity.response.calendar.CalendarModel
import com.sopetit.domain.entity.response.screen.RoutineDetailModel
import com.sopetit.domain.usecase.calendar.GetCalendarUseCase
import com.sopetit.domain.usecase.memberchallenge.DeleteChallengeHistoryUseCase
import com.sopetit.domain.usecase.memberroutine.DeleteDailyRoutineHistoryUseCase
import com.sopetit.domain.usecase.memo.PostWriteMemoUseCase
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val getCalendarUseCase: GetCalendarUseCase,
    private val deleteChallengeHistoryUseCase: DeleteChallengeHistoryUseCase,
    private val deleteDailyRoutineHistoryUseCase: DeleteDailyRoutineHistoryUseCase,
    private val postWriteMemoUseCase: PostWriteMemoUseCase,
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
                deleteChallengeRoutine(routine.routineId)
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

    private fun deleteChallengeRoutine(routineId: Int) {
        viewModelScope.launch {
            deleteChallengeHistoryUseCase(routineId).collect {
                resultResponse(it, { initGetCalendarList(uiState.value.selectedDate) })
            }
        }
    }

    fun writeMemo(memo: String) {
        viewModelScope.launch {
            postWriteMemoUseCase(
                MemoWriteRequestModel(
                    uiState.value.selectedDate.toString(),
                    memo
                )
            ).collect {
                resultResponse(it, { initGetCalendarList(uiState.value.selectedDate) })
            }
        }
    }
}