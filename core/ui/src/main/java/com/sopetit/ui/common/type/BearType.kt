package com.sopetit.ui.common.type

import com.airbnb.lottie.compose.LottieCompositionSpec
import com.sopetit.designsystem.Brown
import com.sopetit.designsystem.Gray
import com.sopetit.design_system.R
import com.sopetit.designsystem.Red
import com.sopetit.designsystem.White

enum class BearType(
    val id: Int,
    val dollType: String,
    val dollFace: Int,
    val dollInBox: Int,
    val dollUpBox: Int,
    val dollLottie: LottieCompositionSpec,
    val dollCrying: Int,
) {
    BROWN(
        1,
        Brown,
        R.drawable.ic_brown_face,
        R.drawable.ic_doll_brown_box_in,
        R.drawable.ic_doll_brown_box_up,
        LottieCompositionSpec.RawRes(R.raw.brown_all),
        R.drawable.ic_bear_brown_crying,
    ),
    GRAY(
        2,
        Gray,
        R.drawable.ic_gray_face,
        R.drawable.ic_doll_gray_box_in,
        R.drawable.ic_doll_gray_box_up,
        LottieCompositionSpec.RawRes(R.raw.gray_all),
        R.drawable.ic_bear_gray_crying,
    ),
    WHITE(
        3,
        White,
        R.drawable.ic_white_face,
        R.drawable.ic_doll_white_box_in,
        R.drawable.ic_doll_white_box_up,
        LottieCompositionSpec.RawRes(R.raw.white_all),
        R.drawable.ic_bear_white_crying,
    ),
    RED(
        4,
        Red,
        R.drawable.ic_red_face,
        R.drawable.ic_doll_red_box_in,
        R.drawable.ic_doll_red_box_up,
        LottieCompositionSpec.RawRes(R.raw.red_all),
        R.drawable.ic_bear_red_crying,
    ),
    ;

    companion object {
        fun getDollFace(dollType: String): Int =
            entries.firstOrNull { it.dollType == dollType }?.dollFace ?: R.drawable.ic_brown_face

        fun getDollBox(
            dollType: String,
            isDollSelected: Boolean,
        ): Int =
            when (isDollSelected) {
                true -> entries.firstOrNull { it.dollType == dollType }?.dollUpBox ?: -1
                false -> entries.firstOrNull { it.dollType == dollType }?.dollInBox ?: -1
            }

        fun getDollResource(dollType: String): LottieCompositionSpec =
            entries.firstOrNull { it.dollType == dollType }?.dollLottie ?: LottieCompositionSpec.RawRes(R.raw.brown_all)

        fun getDollCrying(dollType: String): Int =
            entries.firstOrNull { it.dollType == dollType }?.dollCrying ?: R.drawable.ic_bear_brown_crying
    }
}
