package com.tdd.customroutine

import com.sopetit.ui.base.PageState

data class CustomRoutinePageState (
    val selectedThemeId: Int = 0,
    val routineWriteInput: String = ""
): PageState