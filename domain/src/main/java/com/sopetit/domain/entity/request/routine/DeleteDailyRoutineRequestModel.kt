package com.sopetit.domain.entity.request.routine

data class DeleteDailyRoutineRequestModel(
    val routines: List<Int> = emptyList(),
)