package com.sopetit.home

import com.airbnb.lottie.compose.LottieCompositionSpec
import com.sopetit.design_system.R
import com.sopetit.domain.entity.response.member.GetMemberModel
import com.sopetit.ui.base.PageState

data class HomePageState(
    val isTutorialValid: Boolean = true,
    val homeMemberModel: GetMemberModel = GetMemberModel(),
    val dollHelloResource: LottieCompositionSpec = LottieCompositionSpec.RawRes(R.raw.brown_hello),
    val randomSelectedConversation: String = ""
) : PageState