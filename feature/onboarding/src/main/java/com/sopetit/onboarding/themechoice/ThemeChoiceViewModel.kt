package com.sopetit.onboarding.themechoice

import androidx.lifecycle.viewModelScope
import com.sopetit.design_system.R
import com.sopetit.domain.entity.enums.DollType
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

    fun getSelectedDollType(dollType: DollType) {
        updateState(
            uiState.value.copy(
                selectedDollType = dollType
            )
        )
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

    fun setSelectedThemeIdList(themeId: Int) {
        val newList: MutableList<Int> = mutableListOf()
        newList.addAll(uiState.value.selectedThemeIdList)

        when (uiState.value.selectedThemeIdList.contains(themeId)) {
            true -> {
                newList.remove(themeId)
            }
            false -> {
                if (uiState.value.selectedThemeIdList.size < 3) {
                    newList.add(themeId)
                }
            }
        }

        updateState(
            uiState.value.copy(
                selectedThemeIdList = newList
            )
        )
        Timber.d("[온보딩] selectedThemeIds -> $newList")
    }
}