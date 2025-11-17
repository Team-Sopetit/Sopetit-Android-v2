package com.tdd.setting.deleteuser

import com.sopetit.ui.base.Event

sealed class DeleteUserEvent : Event {
    data object GoBackToLogInPage : DeleteUserEvent()
}
