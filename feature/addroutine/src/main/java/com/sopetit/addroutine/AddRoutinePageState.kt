package com.sopetit.addroutine

import com.sopetit.domain.entity.response.theme.ThemeListItemModel
import com.sopetit.ui.base.PageState

data class AddRoutinePageState(
    val routineThemeList: List<ThemeListItemModel> = emptyList(),
) : PageState