package com.sopetit.data.entity.response.calendar

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCalendarResponseDto(
    @SerialName("memoId")
    val memoId: Int = 0,
    @SerialName("memoContent")
    val memoContent: String = "",
    @SerialName("histories")
    val histories: List<CalendarHistory>,
) {
    @Serializable
    data class CalendarHistory(
        @SerialName("themeId")
        val themeId: Int = 0,
        @SerialName("themeName")
        val themeName: String = "",
        @SerialName("histories")
        val histories: List<History>,
    ) {
        @Serializable
        data class History(
            @SerialName("historyId")
            val historyId: Int = 0,
            @SerialName("content")
            val content: String = "",
            @SerialName("isChallenge")
            val isChallenge: Boolean = false,
        )
    }
}