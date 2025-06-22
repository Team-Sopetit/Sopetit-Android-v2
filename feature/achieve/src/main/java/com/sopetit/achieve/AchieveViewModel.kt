package com.sopetit.achieve

import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AchieveViewModel @Inject constructor(

) : BaseViewModel<AchievePageState>(
    AchievePageState()
) {

    fun setSelectedTab(tab: AchieveTabType) {
        updateState(
            uiState.value.copy(
                selectedTab = tab
            )
        )
    }
}