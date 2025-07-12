package com.tdd.customroutine

import androidx.lifecycle.viewModelScope
import com.sopetit.domain.entity.request.customroutine.CreateCustomRoutineRequestModel
import com.sopetit.domain.entity.response.customroutine.CreateCustomRoutineModel
import com.sopetit.domain.usecase.customroutine.PostCreateCustomRoutineUseCase
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CustomRoutineViewModel @Inject constructor(
    private val postCreateCustomRoutineUseCase: PostCreateCustomRoutineUseCase,
) : BaseViewModel<CustomRoutinePageState>(
    CustomRoutinePageState()
) {

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

    fun updateAlarmActivated() {
        updateState(
            uiState.value.copy(
                isAlarmActivated = !uiState.value.isAlarmActivated
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
        Timber.d("[테스트] -> $data")
    }
}