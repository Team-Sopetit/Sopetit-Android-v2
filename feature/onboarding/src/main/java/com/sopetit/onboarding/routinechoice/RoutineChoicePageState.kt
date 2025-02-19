package com.sopetit.onboarding.routinechoice

import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.ui.base.PageState

data class RoutineChoicePageState (
    val memberModel: CreateMemberModel = CreateMemberModel(),
    val selectedThemeIdList: List<Int> = emptyList()
): PageState