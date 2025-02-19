package com.sopetit.onboarding.routinechoice

import com.sopetit.domain.entity.enums.DollType
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoutineChoiceViewModel @Inject constructor(

): BaseViewModel<RoutineChoicePageState>(
    RoutineChoicePageState()
) {
    fun getSelectedDollType(dollType: DollType) {
        updateState(
            uiState.value.copy(
                selectedDollType = dollType
            )
        )
    }

    fun getSelectedThemeIdList(themeIds: List<Int>) {
        updateState(
            uiState.value.copy(
                selectedThemeIdList = themeIds
            )
        )
    }
}