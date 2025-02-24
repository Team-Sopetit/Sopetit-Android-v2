package com.sopetit.feature

import com.sopetit.ui.base.PageState

data class MainPageState(
    val bottomNavType: BottomNavType = BottomNavType.DEFAULT,
) : PageState