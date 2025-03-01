package com.sopetit.domain.entity.response.memberroutine

data class MemberDailyRoutineTotalModel(
    val routines: List<MemberDailyRoutineListModel> = emptyList(),
)