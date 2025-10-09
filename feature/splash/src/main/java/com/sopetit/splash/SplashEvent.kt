package com.sopetit.splash

import com.sopetit.ui.base.Event

sealed class SplashEvent: Event {
    data object GoToKaKaoLogIn: SplashEvent()
    data object GoToOnboarding: SplashEvent()
    data object GoToHome: SplashEvent()
}