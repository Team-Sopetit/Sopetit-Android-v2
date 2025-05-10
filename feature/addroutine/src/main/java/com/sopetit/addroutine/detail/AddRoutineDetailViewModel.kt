package com.sopetit.addroutine.detail

import androidx.lifecycle.viewModelScope
import com.sopetit.domain.entity.enums.RoutineType
import com.sopetit.domain.entity.response.routine.ChallengeItemModel
import com.sopetit.domain.entity.response.routine.DailyThemeRoutineItemModel
import com.sopetit.domain.entity.response.screen.RoutineDetailModel
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
    private val getChallengeUseCase: GetChallengeUseCase,
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

    private fun onSuccessGetDailyRoutine(data: List<DailyThemeRoutineItemModel>) {
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
        val hasRoutine: ChallengeItemModel = data.firstOrNull { it.hasRoutine } ?: ChallengeItemModel()

        updateState(
            uiState.value.copy(
                challengeList = data,
                hasRoutine = hasRoutine
            )
        )
    }

    fun setRoutineBottomSheetModel(data: ChallengeItemModel): RoutineDetailModel =
        RoutineDetailModel(
            routineId = data.challengeId,
            routineType = RoutineType.Challenge,
            content = data.content,
            explainDetail = data.description,
            time = data.requiredTime,
            place = data.place,
            isJustDetailView = true
        )


    fun updateSelectedChallenge(challenge: ChallengeItemModel) {
        when (uiState.value.selectedChallengeIdList.contains(challenge.challengeId)) {
            true -> {
                updateChallengeList(listOf(-1), ChallengeItemModel())
            }
            false -> {
                if (uiState.value.selectedChallengeIdList[0] == -1) {
                    updateChallengeList(listOf(challenge.challengeId), challenge)
                } else {
                    emitEventFlow(AddRoutineDetailEvent.IsOverChallengeSelected)
                }
            }
        }
    }

    private fun updateChallengeList(list: List<Int>, challenge: ChallengeItemModel) {
        updateState(
            uiState.value.copy(
                selectedChallengeIdList = list,
                selectedChallenge = challenge
            )
        )
    }

    fun updateSelectedDaily(daily: DailyThemeRoutineItemModel) {
        val newList: MutableList<Int> = mutableListOf()

        newList.addAll(uiState.value.selectedDailyIdList)

        when (uiState.value.selectedDailyIdList.contains(daily.id)) {
            true -> {
                newList.remove(daily.id)
            }
            false -> {
                if (daily.existedInMember) {
                    emitEventFlow(AddRoutineDetailEvent.IsRoutineExistedInMember)
                } else {
                    newList.add(daily.id)
                }
            }
        }

        updateState(
            uiState.value.copy(
                selectedDailyIdList = newList
            )
        )
    }

    fun clickAddRoutineBtn() {
        setAddChallenge()
    }

    private fun setAddChallenge() {
        if (uiState.value.hasRoutine == uiState.value.selectedChallenge) {
            // TODO 바텀시트
        } else {
            // TODO 서버통신
        }
    }
}