package com.sopetit.data.entity.response.createroutine

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CustomRoutineResponseDto (
    @SerialName("id")
    val id: Int,
    @SerialName("content")
    val content: String,
    @SerialName("themeId")
    val themeId: Int,
    @SerialName("alarmTime")
    val alarmTime: String
)