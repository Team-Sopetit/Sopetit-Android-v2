package com.sopetit.addroutine.detail

import com.sopetit.design_system.DailyRoutine
import com.sopetit.domain.entity.response.routine.ChallengeItemModel
import com.sopetit.domain.entity.response.routine.DailyRoutineListItemModel
import com.sopetit.domain.entity.response.screen.RoutineDetailModel
import com.sopetit.domain.entity.response.theme.ThemeListItemModel
import com.sopetit.ui.base.PageState

data class AddRoutineDetailPageState (
    val selectedThemeId: Int = -1,
    val selectedTheme: ThemeListItemModel = ThemeListItemModel(),
    val selectedRoutineTab: String = DailyRoutine,
    val dailyRoutineList: List<DailyRoutineListItemModel> = emptyList(),
    val challengeList: List<ChallengeItemModel> = emptyList(),
    val routineBottomSheetModel: RoutineDetailModel = RoutineDetailModel(),
    val selectedChallengeIdList: List<Int> = listOf(-1)
): PageState