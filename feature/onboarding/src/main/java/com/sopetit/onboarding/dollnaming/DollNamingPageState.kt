package com.sopetit.onboarding.dollnaming

import com.sopetit.core.enums.DollType
import com.sopetit.onboarding.model.DollHelloModel
import com.sopetit.ui.base.PageState

data class DollNamingPageState (
    val selectedDollType: DollType = DollType.NONE,
    val dollHelloList: List<DollHelloModel> = emptyList(),
    val dollInputName: String = ""
): PageState