package com.sopetit.progress

import androidx.lifecycle.viewModelScope
import com.sopetit.domain.entity.enums.RoutineType
import com.sopetit.domain.entity.response.memberchallenge.MemberChallengeModel
import com.sopetit.domain.entity.response.memberroutine.MemberDailyRoutineTotalModel
import com.sopetit.domain.usecase.memberchallenge.DeleteMemberChallengeUseCase
import com.sopetit.domain.usecase.memberchallenge.GetMemberChallengeUseCase
import com.sopetit.domain.usecase.memberroutine.DeleteMemberDailyRoutineUseCase
import com.sopetit.domain.usecase.memberroutine.GetMemberDailyRoutineUseCase
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProgressViewModel @Inject constructor(
    private val getMemberDailyRoutineUseCase: GetMemberDailyRoutineUseCase,
    private val getMemberChallengeUseCase: GetMemberChallengeUseCase,
    private val deleteMemberDailyRoutineUseCase: DeleteMemberDailyRoutineUseCase,
    private val deleteMemberChallengeUseCase: DeleteMemberChallengeUseCase,
) : BaseViewModel<ProgressPageState>(
    ProgressPageState()
) {

    init {
        initGetMemberChallenge()
        initGetMemberDailyRoutine()
    }

    private fun initGetMemberChallenge() {
        viewModelScope.launch {
            getMemberChallengeUseCase(request = Unit).collect {
                resultResponse(it, ::onSuccessGetMemberChallenge)
            }
        }
    }

    private fun onSuccessGetMemberChallenge(data: MemberChallengeModel) {
        updateState(
            uiState.value.copy(
                memberChallenge = data
            )
        )
    }

    private fun initGetMemberDailyRoutine() {
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

    fun deleteRoutine(routineType: RoutineType, routineId: Int) {
        if (routineType == RoutineType.Daily)
            deleteDailyRoutine(routineId)
        else deleteChallengeRoutine()
    }

    private fun deleteDailyRoutine(routineId: Int) {
        viewModelScope.launch {
            deleteMemberDailyRoutineUseCase(
                request = listOf(routineId)
            ).collect {
                resultResponse(it, {
                    initGetMemberDailyRoutine()
                })
            }
        }
    }

    private fun deleteChallengeRoutine() {
        viewModelScope.launch {
            deleteMemberChallengeUseCase(request = Unit).collect {
                resultResponse(it, {
                    initGetMemberChallenge()
                })
            }
        }
    }
}