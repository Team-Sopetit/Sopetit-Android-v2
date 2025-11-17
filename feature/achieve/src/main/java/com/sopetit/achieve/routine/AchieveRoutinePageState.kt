package com.sopetit.achieve.routine

import com.sopetit.domain.entity.response.achieve.AchieveRoutineModel
import com.sopetit.ui.base.PageState

data class AchieveRoutinePageState(
    val achieveThemeId: Int = 0,
    val achieveRoutine: AchieveRoutineModel = AchieveRoutineModel(),
) : PageState
