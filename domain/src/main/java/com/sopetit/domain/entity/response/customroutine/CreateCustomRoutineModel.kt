package com.sopetit.domain.entity.response.customroutine

data class CreateCustomRoutineModel (
    val id: Int = 0,
    val content: String = "",
    val themeId: Int = 0,
    val alarmTime: String = ""
)