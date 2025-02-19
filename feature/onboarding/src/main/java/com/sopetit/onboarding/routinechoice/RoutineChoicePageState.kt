package com.sopetit.onboarding.routinechoice

import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.domain.entity.response.routine.DailyRoutineListModel
import com.sopetit.ui.base.PageState

data class RoutineChoicePageState (
    val memberModel: CreateMemberModel = CreateMemberModel(),
//    val selectedThemeIdList: List<Int> = emptyList()
    val routineTotalList: List<DailyRoutineListModel> = emptyList()
): PageState