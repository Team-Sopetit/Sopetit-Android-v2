package com.tdd.customroutine

import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CustomRoutineViewModel @Inject constructor(

): BaseViewModel<CustomRoutinePageState>(
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
}