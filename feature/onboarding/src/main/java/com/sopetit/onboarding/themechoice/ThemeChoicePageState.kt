package com.sopetit.onboarding.themechoice

import com.sopetit.domain.entity.response.theme.ThemeListItemModel
import com.sopetit.ui.base.PageState

data class ThemeChoicePageState (
    val themeList: List<ThemeListItemModel> = emptyList(),
    val themeIconList: List<Int> = emptyList()
): PageState