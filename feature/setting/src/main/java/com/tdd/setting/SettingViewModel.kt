package com.tdd.setting

import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray100
import com.sopetit.design_system.Gray300
import com.sopetit.design_system.LogOutBottomSheetSemiTitle
import com.sopetit.design_system.LogOutBottomSheetTitle
import com.sopetit.design_system.LogOutLeftBtnText
import com.sopetit.design_system.LogOutRightBtnText
import com.sopetit.design_system.Red200
import com.sopetit.ui.base.BaseViewModel
import com.sopetit.ui.common.model.TwoBtnIconModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(): BaseViewModel<SettingPageState>(
    SettingPageState()
) {

    init {
        initSetLogOutBottomSheetModel()
    }

    private fun initSetLogOutBottomSheetModel() {
        val logOutModel = TwoBtnIconModel(
            title = LogOutBottomSheetTitle,
            semiTitle = LogOutBottomSheetSemiTitle,
            leftBtnText = LogOutLeftBtnText,
            rightBtnText = LogOutRightBtnText,
            leftBtnTextColor = Gray300,
            leftBtnColor = Gray100,
            rightBtnColor = Red200,
            rightBtnTextColor = Gray0
        )

        updateState(
            uiState.value.copy(
                logOutModel = logOutModel
            )
        )
    }
}