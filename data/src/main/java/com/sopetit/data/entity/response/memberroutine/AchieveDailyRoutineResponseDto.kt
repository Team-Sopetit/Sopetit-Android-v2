package com.sopetit.data.entity.response.memberroutine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AchieveDailyRoutineResponseDto(
    @SerialName("routineId")
    val routineId: Int = 0,
    @SerialName("isAchieve")
    val isAchieve: Boolean = false,
    @SerialName("achieveCount")
    val achieveCount: Int = 0,
    @SerialName("hasCotton")
    val hasCotton: Boolean = false
)