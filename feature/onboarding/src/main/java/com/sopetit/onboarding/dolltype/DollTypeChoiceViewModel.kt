package com.sopetit.onboarding.dolltype

import com.sopetit.design_system.R
import com.sopetit.domain.entity.enums.DollType
import com.sopetit.onboarding.model.DollTypeModel
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DollTypeChoiceViewModel @Inject constructor(

) : BaseViewModel<DollTypeChoicePageState>(DollTypeChoicePageState()) {

    init {
        initSetDollTypeList()
    }

    private fun initSetDollTypeList() {
        updateState(
            uiState.value.copy(
                dollTypeList = listOf(
                    DollTypeModel(id = 1, dollType = DollType.BROWN, dollInBox = R.drawable.ic_doll_brown_box_in, dollUpBox = R.drawable.ic_doll_brown_box_up),
                    DollTypeModel(id = 2, dollType = DollType.GRAY, dollInBox = R.drawable.ic_doll_gray_box_in, dollUpBox = R.drawable.ic_doll_gray_box_up),
                    DollTypeModel(id = 3, dollType = DollType.WHITE, dollInBox = R.drawable.ic_doll_white_box_in, dollUpBox = R.drawable.ic_doll_white_box_up),
                    DollTypeModel(id = 4, dollType = DollType.RED, dollInBox = R.drawable.ic_doll_red_box_in, dollUpBox = R.drawable.ic_doll_red_box_up),
                )
            )
        )
    }

    fun setSelectedDollType(dollType: DollType) {
        updateState(
            uiState.value.copy(
                selectedDollType = dollType
            )
        )
    }
}