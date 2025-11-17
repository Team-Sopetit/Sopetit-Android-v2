package com.sopetit.data.entity.response.routine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChallengeResponseDto(
    @SerialName("challenges")
    val challenges: List<ChallengeItem> = emptyList(),
) {
    @Serializable
    data class ChallengeItem(
        @SerialName("challengeId")
        val challengeId: Int = 0,
        @SerialName("content")
        val content: String = "",
        @SerialName("description")
        val description: String = "",
        @SerialName("requiredTime")
        val requiredTime: String = "",
        @SerialName("place")
        val place: String = "",
        @SerialName("hasRoutine")
        val hasRoutine: Boolean = false,
    )
}
