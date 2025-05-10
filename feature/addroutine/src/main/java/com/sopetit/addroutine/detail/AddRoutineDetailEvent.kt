package com.sopetit.addroutine.detail

import com.sopetit.ui.base.Event

sealed class AddRoutineDetailEvent: Event {
    data object IsOverChallengeSelected: AddRoutineDetailEvent()
    data object IsRoutineExistedInMember: AddRoutineDetailEvent()
}