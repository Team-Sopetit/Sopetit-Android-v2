package com.sopetit.data.entity.response.theme

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ThemeListResponseDto(
    @SerializedName("themes")
    val themes: List<ThemeListResponseItemDto>,
) {
    @Serializable
    data class ThemeListResponseItemDto(
        @SerializedName("themeId")
        val themeId: Int = -1,
        @SerializedName("title")
        val title: String = "",
        @SerializedName("subTitle")
        val subTitle: String = "",
        @SerializedName("description")
        val description: String = "",
    )
}
