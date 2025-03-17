package com.sopetit.data.entity.request.routine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeleteMemberDailyRoutineRequestDto(
    @SerialName("routines")
    val routines: List<Int>,
)