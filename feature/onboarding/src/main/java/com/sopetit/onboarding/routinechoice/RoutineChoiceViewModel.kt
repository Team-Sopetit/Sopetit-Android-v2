package com.sopetit.onboarding.routinechoice

import androidx.lifecycle.viewModelScope
import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.domain.entity.request.routine.DailyRoutineListRequestModel
import com.sopetit.domain.entity.response.routine.DailyRoutineListThemeTotalModel
import com.sopetit.domain.usecase.routine.GetDailyRoutineUseCase
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RoutineChoiceViewModel @Inject constructor(
    private val getDailyRoutineUseCase: GetDailyRoutineUseCase
) : BaseViewModel<RoutineChoicePageState>(
    RoutineChoicePageState()
) {

    fun getMemberModel(memberModel: CreateMemberModel) {
        updateState(
            uiState.value.copy(
                memberModel = memberModel
            )
        )
        Timber.d("[온보딩] (routineChoice) member -> ${uiState.value.memberModel}")
        initGetDailyRoutineList()
    }

    private fun initGetDailyRoutineList() {
        viewModelScope.launch {
            getDailyRoutineUseCase(request = DailyRoutineListRequestModel(uiState.value.memberModel.selectedThemeIdList)).collect {
                resultResponse(it, ::onSuccessGetDailyRoutine)
            }
        }
    }

    private fun onSuccessGetDailyRoutine(data: DailyRoutineListThemeTotalModel) {
        updateState(
            uiState.value.copy(
                routineTotalList = data.themeTotalList
            )
        )
    }
}