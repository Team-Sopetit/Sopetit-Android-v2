package com.sopetit.onboarding.dollnaming

import com.airbnb.lottie.compose.LottieCompositionSpec
import com.sopetit.domain.entity.enums.DollType
import com.sopetit.design_system.R
import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.ui.base.PageState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

data class DollNamingPageState(
    val memberModel: CreateMemberModel = CreateMemberModel(),
    val selectedDollType: DollType = DollType.NONE,
    val dollHelloResource: LottieCompositionSpec = LottieCompositionSpec.RawRes(R.raw.brown_hello),
    val dollInputName: String = ""
): PageState