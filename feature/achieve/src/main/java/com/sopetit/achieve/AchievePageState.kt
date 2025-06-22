package com.sopetit.achieve

import com.sopetit.design_system.AchieveTabCalendar
import com.sopetit.ui.base.PageState

data class AchievePageState(
    val selectedTab: String = AchieveTabCalendar,
) : PageState