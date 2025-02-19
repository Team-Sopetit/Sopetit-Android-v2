package com.sopetit.onboarding.themechoice

import com.sopetit.domain.entity.enums.DollType
import com.sopetit.domain.entity.response.theme.ThemeListItemModel
import com.sopetit.ui.base.PageState

data class ThemeChoicePageState (
    val selectedDollType: DollType = DollType.NONE,
    val themeList: List<ThemeListItemModel> = emptyList(),
    val themeIconList: List<Int> = emptyList(),
    val selectedThemeIdList: List<Int> = emptyList()
): PageState