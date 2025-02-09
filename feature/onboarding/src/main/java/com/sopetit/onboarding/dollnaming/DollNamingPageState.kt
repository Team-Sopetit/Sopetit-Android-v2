package com.sopetit.onboarding.dollnaming

import com.sopetit.onboarding.model.DollHelloModel
import com.sopetit.ui.base.PageState

data class DollNamingPageState (
    val dollHelloList: List<DollHelloModel> = emptyList()
): PageState