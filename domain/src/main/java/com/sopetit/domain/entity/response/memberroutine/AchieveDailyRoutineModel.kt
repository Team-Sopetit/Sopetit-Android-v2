package com.sopetit.domain.entity.response.memberroutine

data class AchieveDailyRoutineModel(
    val routineId: Int = 0,
    val isAchieve: Boolean = false,
    val achieveCount: Int = 0,
    val hasCotton: Boolean = false,
)
