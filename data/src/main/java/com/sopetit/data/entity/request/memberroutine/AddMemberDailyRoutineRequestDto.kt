package com.sopetit.data.entity.request.memberroutine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddMemberDailyRoutineRequestDto(
    @SerialName("routineIds")
    val routineIds: List<Int> = emptyList(),
)
