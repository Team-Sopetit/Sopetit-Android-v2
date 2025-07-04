package com.sopetit.domain.entity.response.achieve

data class AchieveRoutineModel (
    val id: Int = 0,
    val name: String = "",
    val routineTotalCount: Int = 0,
    val routines: List<AchieveRoutineItem> = emptyList(),
    val challengeTotalCount: Int = 0,
    val challenges: List<AchieveRoutineItem> = emptyList(),
)