package com.sopetit.onboarding.routinechoice

import androidx.lifecycle.viewModelScope
import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.domain.entity.request.routine.DailyRoutineListRequestModel
import com.sopetit.domain.entity.response.routine.DailyRoutineListModel
import com.sopetit.domain.entity.response.routine.DailyRoutineListThemeTotalModel
import com.sopetit.domain.entity.response.theme.ThemeListModel
import com.sopetit.domain.usecase.routine.GetDailyRoutineUseCase
import com.sopetit.domain.usecase.theme.GetThemeListUseCase
import com.sopetit.onboarding.model.SelectedThemeItem
import com.sopetit.ui.base.BaseViewModel
import com.sopetit.ui.common.type.ThemeIconType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RoutineChoiceViewModel @Inject constructor(
    private val getThemeListUseCase: GetThemeListUseCase,
    private val getDailyRoutineUseCase: GetDailyRoutineUseCase
) : BaseViewModel<RoutineChoicePageState>(
    RoutineChoicePageState()
) {

    init {
        initGetThemeList()
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
                originalThemeList = data.themes
            )
        )
    }

    fun getMemberModel(memberModel: CreateMemberModel) {
        updateState(
            uiState.value.copy(
                memberModel = memberModel
            )
        )
        Timber.d("[온보딩] (routineChoice) member -> ${uiState.value.memberModel}")
        initGetDailyRoutineList()
    }

    private fun initGetDailyRoutineList() {
        viewModelScope.launch {
            getDailyRoutineUseCase(request = DailyRoutineListRequestModel(uiState.value.memberModel.selectedThemeIdList)).collect {
                resultResponse(it, ::onSuccessGetDailyRoutine)
            }
        }
    }

    private fun onSuccessGetDailyRoutine(data: DailyRoutineListThemeTotalModel) {
        updateState(
            uiState.value.copy(
                routineTotalList = data.themeTotalList
            )
        )

        setSelectedThemeList(data.themeTotalList)
    }

    private fun setSelectedThemeList(routines: List<DailyRoutineListModel>) {
        val selectedThemeList: List<SelectedThemeItem> = listOf(
            mapSelectedThemeItem(routines, 0),
            mapSelectedThemeItem(routines, 1),
            mapSelectedThemeItem(routines, 2)
        )

        updateState(
            uiState.value.copy(
                chipThemeList = selectedThemeList
            )
        )
    }

    private fun mapSelectedThemeItem(routines: List<DailyRoutineListModel>, index: Int) =
        with(routines) {
            SelectedThemeItem(
                themeId = get(index).themeId,
                title = uiState.value.originalThemeList.first { get(index).themeId == it.themeId }.title,
                themeIcon = ThemeIconType.getThemeIcon(get(index).themeId)
            )
        }

    fun setSelectedThemeId(themeId: Int) {
        updateState(
            uiState.value.copy(
                selectedThemeId = themeId
            )
        )
    }
}