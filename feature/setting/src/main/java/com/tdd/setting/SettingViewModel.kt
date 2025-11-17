package com.tdd.setting

import androidx.lifecycle.viewModelScope
import com.sopetit.designsystem.Gray0
import com.sopetit.designsystem.Gray100
import com.sopetit.designsystem.Gray300
import com.sopetit.designsystem.LogOutBottomSheetSemiTitle
import com.sopetit.designsystem.LogOutBottomSheetTitle
import com.sopetit.designsystem.LogOutLeftBtnText
import com.sopetit.designsystem.LogOutRightBtnText
import com.sopetit.designsystem.Red200
import com.sopetit.domain.entity.response.version.VersionModel
import com.sopetit.domain.usecase.auth.PostLogOutUseCase
import com.sopetit.domain.usecase.version.GetVersionUseCase
import com.sopetit.ui.base.BaseViewModel
import com.sopetit.ui.common.model.TwoBtnIconModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val postLogOutUseCase: PostLogOutUseCase,
    private val getVersionUseCase: GetVersionUseCase
): BaseViewModel<SettingPageState>(
    SettingPageState()
) {

    init {
        initSetLogOutBottomSheetModel()
        initSetAppVersion()
    }

    private fun initSetAppVersion() {
        viewModelScope.launch {
            getVersionUseCase(Unit).collect { resultResponse(it, ::onSuccessAppVersion) }
        }
    }

    private fun onSuccessAppVersion(data: VersionModel) {
        updateState(
            uiState.value.copy(
                appVersion = data.androidVersion.appVersion
            )
        )
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

    fun postLogOut(isSelected: Boolean) {
        if (isSelected) {
            viewModelScope.launch {
                postLogOutUseCase(Unit).collect { resultResponse(it, {} )}

                emitEventFlow(SettingEvent.GoBackToLogInPage)
            }
        }
    }
}