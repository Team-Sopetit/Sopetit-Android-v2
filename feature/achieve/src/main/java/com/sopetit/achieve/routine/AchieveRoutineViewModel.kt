package com.sopetit.achieve.routine

import androidx.lifecycle.viewModelScope
import com.sopetit.domain.entity.response.achieve.AchieveRoutineModel
import com.sopetit.domain.usecase.achieve.GetAchieveRoutineUseCase
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AchieveRoutineViewModel @Inject constructor(
    private val getAchieveRoutineUseCase: GetAchieveRoutineUseCase,
) : BaseViewModel<AchieveRoutinePageState>(
    AchieveRoutinePageState()
) {

    fun getAchieveThemeRoutine(themeId: Int) {
        viewModelScope.launch {
            setAchieveThemeId(themeId)

            getAchieveRoutineUseCase(themeId).collect {
                resultResponse(
                    it,
                    ::onSuccessAchieveThemeRoutine
                )
            }
        }
    }

    private fun setAchieveThemeId(themeId: Int) {
        updateState(
            uiState.value.copy(
                achieveThemeId = themeId
            )
        )
    }

    private fun onSuccessAchieveThemeRoutine(data: AchieveRoutineModel) {
        Timber.d("[테스트] -> $data")
        updateState(
            uiState.value.copy(
                achieveRoutine = data
            )
        )
    }
}