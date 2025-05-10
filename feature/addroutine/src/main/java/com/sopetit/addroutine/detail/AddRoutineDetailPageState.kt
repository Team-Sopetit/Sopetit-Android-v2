package com.sopetit.addroutine.detail

import com.sopetit.domain.entity.response.theme.ThemeListItemModel
import com.sopetit.ui.base.PageState

data class AddRoutineDetailPageState (
    val selectedThemeId: Int = -1,
    val selectedTheme: ThemeListItemModel = ThemeListItemModel()
): PageState