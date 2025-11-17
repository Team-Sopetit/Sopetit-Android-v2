package com.sopetit.data.entity.response.routine

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class DailyRoutineListResponseDto(
    @SerializedName("themes")
    val themes: List<DailyRoutineThemeListDto>,
) {
    @Serializable
    data class DailyRoutineThemeListDto(
        @SerializedName("themeId")
        val themeId: Int = -1,
        @SerializedName("routines")
        val routines: List<DailyRoutineListItemDto>,
    ) {
        @Serializable
        data class DailyRoutineListItemDto(
            @SerializedName("routineId")
            val routineId: Int = -1,
            @SerializedName("content")
            val content: String = "",
        )
    }
}
