package com.sopetit.onboarding.dollnaming

import com.airbnb.lottie.compose.LottieCompositionSpec
import com.sopetit.domain.entity.enums.DollType
import com.sopetit.design_system.R
import com.sopetit.ui.base.PageState

data class DollNamingPageState(
    val selectedDollType: DollType = DollType.NONE,
    val dollHelloResource: LottieCompositionSpec = LottieCompositionSpec.RawRes(R.raw.brown_hello),
    val dollInputName: String = ""
): PageState