package com.sopetit.addroutine.detail

import androidx.lifecycle.viewModelScope
import com.sopetit.domain.entity.response.routine.ChallengeItemModel
import com.sopetit.domain.entity.response.routine.DailyRoutineListItemModel
import com.sopetit.domain.entity.response.theme.ThemeListItemModel
import com.sopetit.domain.usecase.routine.GetChallengeUseCase
import com.sopetit.domain.usecase.routine.GetDailyThemeRoutineUseCase
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddRoutineDetailViewModel @Inject constructor(
    private val getDailyThemeRoutineUseCase: GetDailyThemeRoutineUseCase,
    private val getChallengeUseCase: GetChallengeUseCase
) : BaseViewModel<AddRoutineDetailPageState>(
    AddRoutineDetailPageState()
) {

    fun setSelectedTheme(theme: ThemeListItemModel) {
        updateState(
            uiState.value.copy(
                selectedThemeId = theme.themeId,
                selectedTheme = theme
            )
        )

        initSetDailyRoutineList(theme.themeId)
        initSetChallenge(theme.themeId)
    }

    fun setSelectedRoutineTab(tab: String) {
        updateState(
            uiState.value.copy(
                selectedRoutineTab = tab
            )
        )
    }

    private fun initSetDailyRoutineList(themeId: Int) {
        viewModelScope.launch {
            getDailyThemeRoutineUseCase(request = themeId).collect {
                resultResponse(it, ::onSuccessGetDailyRoutine)
            }
        }
    }

    private fun onSuccessGetDailyRoutine(data: List<DailyRoutineListItemModel>) {
        updateState(
            uiState.value.copy(
                dailyRoutineList = data
            )
        )
    }

    private fun initSetChallenge(themeId: Int) {
        viewModelScope.launch {
            getChallengeUseCase(request = themeId).collect {
                resultResponse(it, ::onSuccessGetChallenge)
            }
        }
    }

    private fun onSuccessGetChallenge(data: List<ChallengeItemModel>) {
        updateState(
            uiState.value.copy(
                challengeList = data
            )
        )
    }
}