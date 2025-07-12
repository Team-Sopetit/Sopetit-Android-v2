package com.sopetit.domain.entity.request.customroutine

data class CreateCustomRoutineRequestModel (
    val content: String = "",
    val themeId: Int = 0,
    val alarmTime: String = ""
)