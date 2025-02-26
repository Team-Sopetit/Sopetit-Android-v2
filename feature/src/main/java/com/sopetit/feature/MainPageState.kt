package com.sopetit.feature

import com.sopetit.domain.entity.response.screen.TutorialModel
import com.sopetit.ui.base.PageState

data class MainPageState(
    val bottomNavType: BottomNavType = BottomNavType.DEFAULT,
    val tutorials: List<TutorialModel> = emptyList()
) : PageState