package com.sopetit.data.entity.request.customroutine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateCustomRoutineRequestDto (
    @SerialName("content")
    val content: String,
    @SerialName("themeId")
    val themeId: Int,
    @SerialName("alarmTime")
    val alarmTime: String
)