package com.sopetit.progress

import androidx.lifecycle.viewModelScope
import com.sopetit.domain.entity.response.memberroutine.MemberDailyRoutineTotalModel
import com.sopetit.domain.usecase.memberroutine.GetMemberDailyRoutineUseCase
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProgressViewModel @Inject constructor(
    private val getMemberDailyRoutineUseCase: GetMemberDailyRoutineUseCase,
) : BaseViewModel<ProgressPageState>(
    ProgressPageState()
) {

    init {
        getMemberDailyRoutine()
    }

    private fun getMemberDailyRoutine() {
        viewModelScope.launch {
            getMemberDailyRoutineUseCase(request = Unit).collect {
                resultResponse(it, ::onSuccessGetMemberDailyRoutine)
            }
        }
    }

    private fun onSuccessGetMemberDailyRoutine(data: MemberDailyRoutineTotalModel) {
        updateState(
            uiState.value.copy(
                memberDailyRoutineList = data.routines
            )
        )
    }
}