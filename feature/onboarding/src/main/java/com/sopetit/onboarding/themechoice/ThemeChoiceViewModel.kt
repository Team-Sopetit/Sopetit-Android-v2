package com.sopetit.onboarding.themechoice

import androidx.lifecycle.viewModelScope
import com.sopetit.domain.entity.enums.DollType
import com.sopetit.domain.entity.request.CreateMemberModel
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
        initGetThemeList()
    }

//    fun getSelectedDollType(dollType: DollType) {
//        updateState(
//            uiState.value.copy(
//                selectedDollType = dollType
//            )
//        )
//        Timber.d("[온보딩] themechoice VM dollType -> ${uiState.value.selectedDollType}")
//    }

    fun getMemberModel(memberModel: CreateMemberModel) {
        updateState(
            uiState.value.copy(
                memberModel = memberModel
            )
        )
        Timber.d("[온보딩] (themeChoice) member -> ${uiState.value.memberModel}")
    }

    fun updateMemberModel() = CreateMemberModel(dollType = uiState.value.memberModel.dollType, dollName = uiState.value.memberModel.dollName, selectedThemeIdList = uiState.value.selectedThemeIdList)

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