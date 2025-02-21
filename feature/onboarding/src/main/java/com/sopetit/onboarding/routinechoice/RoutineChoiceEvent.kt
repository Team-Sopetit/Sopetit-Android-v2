package com.sopetit.onboarding.routinechoice

import com.sopetit.ui.base.Event

sealed class RoutineChoiceEvent : Event {
    data object OnSuccessCreateMember : RoutineChoiceEvent()
    data object IsOverRoutineNumSize: RoutineChoiceEvent()
}