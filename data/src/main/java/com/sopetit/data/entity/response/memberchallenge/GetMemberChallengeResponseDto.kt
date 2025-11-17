package com.sopetit.data.entity.response.memberchallenge

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GetMemberChallengeResponseDto(
    @SerializedName("memberChallengeId")
    val memberChallengeId: Int = -1,
    @SerializedName("theme")
    val theme: ChallengeTheme,
    @SerializedName("content")
    val content: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("place")
    val place: String = "",
    @SerializedName("timeTaken")
    val timeTaken: String = "",
) {
    @Serializable
    data class ChallengeTheme(
        @SerializedName("themeId")
        val themeId: Int = -1,
        @SerializedName("themeName")
        val themeName: String = "",
    )
}
