package com.tdd.customroutine

import com.sopetit.domain.entity.enums.CustomScreenType
import com.sopetit.domain.entity.enums.RoutineType
import com.sopetit.domain.entity.response.screen.ModifyRoutineModel
import com.sopetit.ui.base.PageState

data class CustomRoutinePageState(
    val selectedThemeId: Int = 0,
    val routineWriteInput: String = "",
    val isAlarmActivated: Boolean = false,
    val modifyRoutine: ModifyRoutineModel = ModifyRoutineModel(),
    val modifyType: RoutineType = RoutineType.Custom,
    val customScreenType: CustomScreenType = CustomScreenType.Create,
) : PageState
