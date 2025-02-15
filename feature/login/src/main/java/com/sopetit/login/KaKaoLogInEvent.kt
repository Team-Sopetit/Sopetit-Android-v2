package com.sopetit.login

import com.sopetit.ui.base.Event

sealed class KaKaoLogInEvent: Event {
    data object OnSuccessLogIn: KaKaoLogInEvent()
}