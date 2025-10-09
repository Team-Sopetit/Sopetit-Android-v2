package com.sopetit.splash

import com.sopetit.ui.base.PageState

data class SplashPageState (
    val skipLogin: Boolean = false,
    val isMemberDollExist: Boolean = false
): PageState