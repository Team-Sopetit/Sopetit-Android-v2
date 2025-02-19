package com.sopetit.feature

import com.sopetit.domain.entity.enums.DollType
import com.sopetit.ui.base.PageState

data class MainPageState (
    val temp: String = "",
    val selectedDollType: DollType = DollType.NONE
): PageState