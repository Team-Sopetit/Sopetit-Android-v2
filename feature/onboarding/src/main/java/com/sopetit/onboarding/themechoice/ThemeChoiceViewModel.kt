package com.sopetit.onboarding.themechoice

import androidx.lifecycle.viewModelScope
import com.sopetit.design_system.R
import com.sopetit.domain.entity.response.theme.ThemeListModel
import com.sopetit.domain.usecase.theme.GetThemeListUseCase
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ThemeChoiceViewModel @Inject constructor(
    private val getThemeListUseCase: GetThemeListUseCase
) : BaseViewModel<ThemeChoicePageState>(
    ThemeChoicePageState()
) {

    init {
        initSetThemeIconList()
        initGetThemeList()
    }

    private fun initSetThemeIconList() {
        updateState(
            uiState.value.copy(
                themeIconList = listOf(
                    R.drawable.ic_theme1,
                    R.drawable.ic_theme2,
                    R.drawable.ic_theme3,
                    R.drawable.ic_theme4,
                    R.drawable.ic_theme5,
                    R.drawable.ic_theme6,
                    R.drawable.ic_theme7
                )
            )
        )
    }

    private fun initGetThemeList() {
        viewModelScope.launch {
            getThemeListUseCase(request = Unit).collect {
                resultResponse(it, ::onSuccessGetThemeList)
            }
        }
    }

    private fun onSuccessGetThemeList(data: ThemeListModel) {
        updateState(
            uiState.value.copy(
                themeList = data.themes
            )
        )
        Timber.d("[온보딩] theme list -> $data")
    }
}