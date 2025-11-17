package com.sopetit.onboarding.themechoice

import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.domain.entity.response.theme.ThemeListItemModel
import com.sopetit.ui.base.PageState

data class ThemeChoicePageState(
    val isFirstChoicePage: Boolean = true,
    val memberModel: CreateMemberModel = CreateMemberModel(),
    val themeList: List<ThemeListItemModel> = emptyList(),
    val selectedThemeIdList: List<Int> = emptyList(),
) : PageState
