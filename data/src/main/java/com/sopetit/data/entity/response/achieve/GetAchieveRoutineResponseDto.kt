package com.sopetit.data.entity.response.achieve

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAchieveRoutineResponseDto(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("name")
    val name: String = "",
    @SerialName("routineTotalCount")
    val routineTotalCount: Int = 0,
    @SerialName("routines")
    val routines: List<RoutineItem> = emptyList(),
    @SerialName("challengeTotalCount")
    val challengeTotalCount: Int = 0,
    @SerialName("challenges")
    val challenges: List<ChallengeItem> = emptyList(),
) {
    @Serializable
    data class RoutineItem(
        @SerialName("content")
        val content: String = "",
        @SerialName("achievedCount")
        val achievedCount: Int = 0,
        @SerialName("startedAt")
        val startedAt: String = "",
    )

    @Serializable
    data class ChallengeItem(
        @SerialName("content")
        val content: String = "",
        @SerialName("achievedCount")
        val achievedCount: Int = 0,
        @SerialName("startedAt")
        val startedAt: String = "",
    )
}