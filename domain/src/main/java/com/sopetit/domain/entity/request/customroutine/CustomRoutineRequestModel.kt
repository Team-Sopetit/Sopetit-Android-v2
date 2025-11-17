package com.sopetit.domain.entity.request.customroutine

data class CustomRoutineRequestModel(
    val content: String = "",
    val themeId: Int = 0,
    val alarmTime: String = "",
)
