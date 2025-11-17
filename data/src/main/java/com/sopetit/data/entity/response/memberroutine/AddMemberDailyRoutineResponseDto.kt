package com.sopetit.data.entity.response.memberroutine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddMemberDailyRoutineResponseDto(
    @SerialName("ids")
    val ids: List<Int> = emptyList(),
)
