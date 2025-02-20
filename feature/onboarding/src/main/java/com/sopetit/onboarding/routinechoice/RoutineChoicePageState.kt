package com.sopetit.onboarding.routinechoice

import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.domain.entity.response.routine.DailyRoutineListModel
import com.sopetit.domain.entity.response.theme.ThemeListItemModel
import com.sopetit.onboarding.model.SelectedThemeItem
import com.sopetit.ui.base.PageState

data class RoutineChoicePageState(
    val memberModel: CreateMemberModel = CreateMemberModel(),
    val originalThemeList: List<ThemeListItemModel> = emptyList(),
    val chipThemeList: List<SelectedThemeItem> = emptyList(),
    val routineTotalList: List<DailyRoutineListModel> = emptyList()
) : PageState