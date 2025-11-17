package com.sopetit.achieve

import com.sopetit.ui.base.PageState

data class AchievePageState(
    val selectedTab: AchieveTabType = AchieveTabType.TabStat,
) : PageState
