package com.sopetit.onboarding.dolltype

import com.sopetit.domain.entity.enums.DollType
import com.sopetit.ui.base.PageState

data class DollTypeChoicePageState (
    val selectedDollType: DollType = DollType.NONE
): PageState