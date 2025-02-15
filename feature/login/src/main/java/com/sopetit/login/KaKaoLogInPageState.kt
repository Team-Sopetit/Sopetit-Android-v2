package com.sopetit.login

import com.sopetit.ui.base.PageState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow

data class KaKaoLogInPageState (
    val isKaKaoLogInSuccess: SharedFlow<Boolean> = MutableStateFlow(false)
): PageState