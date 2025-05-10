package com.sopetit.data.entity.response.routine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyThemeRoutineResponseDto(
    @SerialName("routines")
    val routines: List<RoutineListItem> = emptyList(),
) {
    @Serializable
    data class RoutineListItem(
        @SerialName("routineId")
        val routineId: Int = 0,
        @SerialName("content")
        val content: String = "",
    )
}