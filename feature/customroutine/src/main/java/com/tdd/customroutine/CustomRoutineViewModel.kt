package com.tdd.customroutine

import androidx.lifecycle.viewModelScope
import com.sopetit.design_system.TimeAM
import com.sopetit.design_system.TimeMinuteZero
import com.sopetit.domain.entity.request.customroutine.CreateCustomRoutineRequestModel
import com.sopetit.domain.entity.response.customroutine.CreateCustomRoutineModel
import com.sopetit.domain.entity.response.screen.ModifyRoutineModel
import com.sopetit.domain.usecase.customroutine.PostCreateCustomRoutineUseCase
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomRoutineViewModel @Inject constructor(
    private val postCreateCustomRoutineUseCase: PostCreateCustomRoutineUseCase,
) : BaseViewModel<CustomRoutinePageState>(
    CustomRoutinePageState()
) {

    fun setModifyRoutine(modifyRoutineModel: ModifyRoutineModel) {
        updateState(
            uiState.value.copy(
                selectedThemeId = modifyRoutineModel.themeId,
                routineWriteInput = modifyRoutineModel.content,
                modifyType = modifyRoutineModel.routineType,
                customScreenType = modifyRoutineModel.customScreenType
            )
        )
    }

    fun setSelectedTheme(themeId: Int) {
        updateState(
            uiState.value.copy(
                selectedThemeId = themeId
            )
        )
    }

    fun onRoutineValueChange(newValue: String) {
        updateState(
            uiState.value.copy(
                routineWriteInput = newValue
            )
        )
    }

    fun updateAlarmActivated(isActivated: Boolean) {
        updateState(
            uiState.value.copy(
                isAlarmActivated = isActivated
            )
        )
    }

    fun createCustomRoutine(alarmTime: String) {
        viewModelScope.launch {
            postCreateCustomRoutineUseCase(
                CreateCustomRoutineRequestModel(
                    uiState.value.routineWriteInput,
                    uiState.value.selectedThemeId,
                    alarmTime
                )
            ).collect { resultResponse(it, ::onSuccessCreateCustomRoutine) }
        }
    }

    private fun onSuccessCreateCustomRoutine(data: CreateCustomRoutineModel) {
        emitEventFlow(CustomRoutineEvent.GoToProgressPage)
    }

    fun convertTimeState(time: String): Int {
        return when (time == TimeAM) {
            true -> 0
            false -> 1
        }
    }

    fun convertMinuteState(time: String): Int {
        return when (time == TimeMinuteZero) {
            true -> 0
            false -> 1
        }
    }
}