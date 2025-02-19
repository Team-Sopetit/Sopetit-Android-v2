package com.sopetit.data.entity.request.routine

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class DailyRoutineListRequestDto(
    @SerializedName("themeIds")
    val themeIds: List<Int> = emptyList()
)