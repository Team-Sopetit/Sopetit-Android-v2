package com.sopetit.home

import com.airbnb.lottie.compose.LottieCompositionSpec
import com.sopetit.design_system.Brown
import com.sopetit.design_system.R
import com.sopetit.domain.entity.response.member.GetMemberModel
import com.sopetit.domain.entity.response.screen.TutorialModel
import com.sopetit.ui.base.PageState
import com.sopetit.ui.common.model.TwoBtnDialogModel
import com.sopetit.ui.common.type.LottieType

data class HomePageState(
    val isTutorialValid: Boolean = true,
    val tutorialList: List<TutorialModel> = emptyList(),
    val homeMemberModel: GetMemberModel = GetMemberModel(),
    val dollHelloResource: LottieCompositionSpec = LottieCompositionSpec.RawRes(R.raw.brown_hello),
    val dollCurrentResource: LottieCompositionSpec = LottieCompositionSpec.RawRes(0),
    val dollCurrentMode: LottieType = LottieType.HELLO,
    val dollType: String = Brown,
    val dollEatingDailyResource: LottieCompositionSpec = LottieCompositionSpec.RawRes(R.raw.brown_eating_daily),
    val dollEatingHappyResource: LottieCompositionSpec = LottieCompositionSpec.RawRes(R.raw.brown_eating_happy),
    val randomSelectedConversation: String = "",
    val dailyCottonCount: Int = 0,
    val happinessCottonCount: Int = 0,
    val feedBackDialog: TwoBtnDialogModel = TwoBtnDialogModel(),
) : PageState