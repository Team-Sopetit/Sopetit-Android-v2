package com.sopetit.addroutine.detail

import androidx.lifecycle.viewModelScope
import com.sopetit.domain.entity.enums.RoutineType
import com.sopetit.domain.entity.response.memberchallenge.MemberChallengeModel
import com.sopetit.domain.entity.response.routine.ChallengeChangeModel
import com.sopetit.domain.entity.response.routine.ChallengeItemModel
import com.sopetit.domain.entity.response.routine.DailyThemeRoutineItemModel
import com.sopetit.domain.entity.response.screen.RoutineDetailModel
import com.sopetit.domain.entity.response.theme.ChallengeThemeItemModel
import com.sopetit.domain.entity.response.theme.ThemeListItemModel
import com.sopetit.domain.usecase.memberchallenge.AddMemberChallengeUseCase
import com.sopetit.domain.usecase.memberroutine.AddDailyMemberRoutineUseCase
import com.sopetit.domain.usecase.routine.GetChallengeUseCase
import com.sopetit.domain.usecase.routine.GetDailyThemeRoutineUseCase
import com.sopetit.ui.base.BaseViewModel
import com.sopetit.ui.common.type.ThemeIconType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddRoutineDetailViewModel @Inject constructor(
    private val getDailyThemeRoutineUseCase: GetDailyThemeRoutineUseCase,
    private val getChallengeUseCase: GetChallengeUseCase,
    private val addDailyMemberRoutineUseCase: AddDailyMemberRoutineUseCase,
    private val addMemberChallengeUseCase: AddMemberChallengeUseCase
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
        val hasRoutine: ChallengeItemModel =
            data.firstOrNull { it.hasRoutine } ?: ChallengeItemModel()

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
        if (uiState.value.selectedDailyIdList.isNotEmpty()) {
            addDailyRoutine()
        }
        if (uiState.value.selectedChallengeIdList[0] != -1) {
            setAddChallenge()
        }
    }

    private fun addDailyRoutine() {
        viewModelScope.launch {
            addDailyMemberRoutineUseCase(uiState.value.selectedDailyIdList).collect{
                resultResponse(it, ::onSuccessDailyRoutine)
            }
        }
    }

    private fun onSuccessDailyRoutine(data: List<Int>) {
        Timber.d("[루틴] -> 데일리 루틴 추가 $data")
        emitEventFlow(AddRoutineDetailEvent.GoBackToProgressRoutine)
    }

    private fun setAddChallenge() {
        if (uiState.value.hasRoutine != ChallengeItemModel()) {
            emitEventFlow(AddRoutineDetailEvent.HasChallengeRoutine)
        } else {
            addChallenge()
        }
    }

    private fun addChallenge() {
        viewModelScope.launch {
            addMemberChallengeUseCase(uiState.value.selectedChallenge.challengeId).collect{
                resultResponse(it, ::onSuccessAddChallenge)
            }
        }
    }

    private fun onSuccessAddChallenge(data: Int) {
        Timber.d("[루틴] -> 챌린지 루틴 추가 $data")
        emitEventFlow(AddRoutineDetailEvent.GoBackToProgressRoutine)
    }

    fun setChangeChallenge(): ChallengeChangeModel {
        val hasChallenge = uiState.value.hasRoutine
        val changeChallenge = uiState.value.selectedChallenge
        val theme = uiState.value.selectedTheme

        val challenge = ChallengeChangeModel(
            hasChallenge = MemberChallengeModel(
                memberChallengeId = hasChallenge.challengeId,
                theme = ChallengeThemeItemModel(
                    themeId = theme.themeId,
                    themeName = ThemeIconType.getThemeName(theme.themeId),
                ),
                content = hasChallenge.content,
                description = hasChallenge.description,
                place = hasChallenge.place,
                timeTaken = hasChallenge.requiredTime
            ),
            changeChallenge = MemberChallengeModel(
                memberChallengeId = changeChallenge.challengeId,
                theme = ChallengeThemeItemModel(
                    themeId = theme.themeId,
                    themeName = ThemeIconType.getThemeName(theme.themeId),
                ),
                content = changeChallenge.content,
                description = changeChallenge.description,
                place = changeChallenge.place,
                timeTaken = changeChallenge.requiredTime
            )
        )

        return challenge
    }
}