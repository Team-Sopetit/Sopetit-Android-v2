package com.sopetit.addroutine.detail

import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddRoutineDetailViewModel @Inject constructor(

): BaseViewModel<AddRoutineDetailPageState>(
    AddRoutineDetailPageState()
) {

    fun setSelectedThemeId(themeId: Int) {
        updateState(
            uiState.value.copy(
                selectedThemeId = themeId
            )
        )
    }
}