package com.sopetit.domain.entity.response.memberroutine

data class MemberDailyRoutineListItemModel(
    val routineId: Int = -1,
    val content: String = "",
    val achieveCount: Int = -1,
    val isAchieve: Boolean = false,
)