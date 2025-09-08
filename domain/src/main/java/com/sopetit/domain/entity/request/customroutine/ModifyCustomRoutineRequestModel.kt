package com.sopetit.domain.entity.request.customroutine

data class ModifyCustomRoutineRequestModel (
    val themeId: Int = 0,
    val body: CustomRoutineRequestModel = CustomRoutineRequestModel()
)