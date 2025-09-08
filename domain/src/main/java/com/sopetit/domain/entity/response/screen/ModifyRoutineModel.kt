package com.sopetit.domain.entity.response.screen

import com.sopetit.domain.entity.enums.CustomScreenType
import com.sopetit.domain.entity.enums.RoutineType

data class ModifyRoutineModel (
    val routineId: Int = 0,
    val routineType: RoutineType = RoutineType.Custom,
    val customScreenType: CustomScreenType = CustomScreenType.Create,
    val content: String = "",
    val alarmTime: String? = null,
    val themeId: Int = 0
)