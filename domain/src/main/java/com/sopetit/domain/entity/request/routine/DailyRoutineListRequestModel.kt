package com.sopetit.domain.entity.request.routine

data class DailyRoutineListRequestModel(
    val themeIdList: List<Int> = emptyList(),
)
