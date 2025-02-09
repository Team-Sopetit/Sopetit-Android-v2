package com.sopetit.onboarding.dolltype

import com.sopetit.domain.entity.enums.DollType
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DollTypeChoiceViewModel @Inject constructor(

) : BaseViewModel<DollTypeChoicePageState>(DollTypeChoicePageState()) {

    fun setSelectedDollType(dollType: DollType) {
        updateState(
            uiState.value.copy(
                selectedDollType = dollType
            )
        )
    }
}