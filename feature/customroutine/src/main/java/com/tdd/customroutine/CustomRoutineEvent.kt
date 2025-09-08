package com.tdd.customroutine

import com.sopetit.ui.base.Event

sealed class CustomRoutineEvent: Event {
    data object GoToProgressPage: CustomRoutineEvent()
}