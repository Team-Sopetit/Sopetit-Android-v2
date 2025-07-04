package com.sopetit.data.entity.response.achieve

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAchieveResponseDto (
    @SerialName("achievedCount")
    val achievedCount: Int = 0,
    @SerialName("themes")
    val themes: List<AchieveTheme> = emptyList()
) {
    @Serializable
    data class AchieveTheme(
        @SerialName("id")
        val id: Int = 0,
        @SerialName("name")
        val name: String = "",
        @SerialName("achievedCount")
        val achievedCount: Int = 0
    )
}