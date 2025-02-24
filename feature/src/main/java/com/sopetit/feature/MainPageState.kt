package com.sopetit.feature

import com.sopetit.core.enums.BottomNavType
import com.sopetit.ui.base.PageState

data class MainPageState(
    val bottomNavType: BottomNavType = BottomNavType.DEFAULT,
) : PageState