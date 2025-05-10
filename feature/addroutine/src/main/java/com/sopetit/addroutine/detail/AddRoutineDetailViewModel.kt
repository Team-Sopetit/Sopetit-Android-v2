package com.sopetit.addroutine.detail

import com.sopetit.domain.entity.response.theme.ThemeListItemModel
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddRoutineDetailViewModel @Inject constructor(

): BaseViewModel<AddRoutineDetailPageState>(
    AddRoutineDetailPageState()
) {

    fun setSelectedTheme(theme: ThemeListItemModel) {
        updateState(
            uiState.value.copy(
                selectedThemeId = theme.themeId,
                selectedTheme = theme
            )
        )
    }

    fun setSelectedRoutineTab(tab: String) {
        updateState(
            uiState.value.copy(
                selectedRoutineTab = tab
            )
        )
    }
}