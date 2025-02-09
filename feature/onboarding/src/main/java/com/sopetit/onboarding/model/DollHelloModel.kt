package com.sopetit.onboarding.model

import com.airbnb.lottie.compose.LottieCompositionSpec
import com.sopetit.domain.entity.enums.DollType

data class DollHelloModel (
    val id: Long = -1,
    val dollType: DollType = DollType.NONE,
    val resource: LottieCompositionSpec
)