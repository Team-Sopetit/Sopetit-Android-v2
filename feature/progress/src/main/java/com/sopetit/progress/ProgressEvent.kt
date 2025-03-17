package com.sopetit.progress

import com.sopetit.ui.base.Event

sealed class ProgressEvent : Event {
    data object OnShowChallengeAchieveSom : ProgressEvent()
}