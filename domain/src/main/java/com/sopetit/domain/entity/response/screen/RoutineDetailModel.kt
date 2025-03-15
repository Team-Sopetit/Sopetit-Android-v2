package com.sopetit.domain.entity.response.screen

import com.sopetit.domain.entity.enums.RoutineType

data class RoutineDetailModel(
    val id: Int = 0,
    val routineType: RoutineType = RoutineType.Daily,
    val content: String = "",
    val explainDetail: String = "",
    val time: String = "",
    val place: String = "",
)