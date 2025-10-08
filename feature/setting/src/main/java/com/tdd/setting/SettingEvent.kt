package com.tdd.setting

import com.sopetit.ui.base.Event

sealed class SettingEvent: Event {
    data object GoBackToLogInPage: SettingEvent()
}