package com.sopetit.domain.entity.response.screen

import com.sopetit.domain.entity.enums.RoutineType

data class RoutineDetailModel(
    val routineId: Int = 0,
    val routineType: RoutineType = RoutineType.Daily,
    val content: String = "",
    val explainDetail: String = "",
    val time: String = "",
    val place: String = "",
    val isJustDetailView: Boolean = false,
    val alarmTime: String = "",
)
