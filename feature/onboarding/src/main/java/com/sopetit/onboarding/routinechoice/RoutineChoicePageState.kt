package com.sopetit.onboarding.routinechoice

import com.sopetit.domain.entity.enums.DollType
import com.sopetit.ui.base.PageState

data class RoutineChoicePageState (
    val selectedDollType: DollType = DollType.NONE,
    val selectedThemeIdList: List<Int> = emptyList()
): PageState