package com.sopetit.progress

import com.sopetit.ui.base.Event

sealed class ProgressEvent : Event {
    data object OnShowChallengeAchieveSom : ProgressEvent()

    data object OnShowDailyAchieveSom : ProgressEvent()

    data object OnShowDailyAchieveHasSomFalse : ProgressEvent()

    data object OnShowDailyAchieveCancel : ProgressEvent()
}
