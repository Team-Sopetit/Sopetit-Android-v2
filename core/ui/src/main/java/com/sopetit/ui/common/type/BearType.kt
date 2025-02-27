package com.sopetit.ui.common.type

import com.airbnb.lottie.compose.LottieCompositionSpec
import com.sopetit.design_system.Brown
import com.sopetit.design_system.Gray
import com.sopetit.design_system.R
import com.sopetit.design_system.Red
import com.sopetit.design_system.White

enum class BearType(
    val id: Int,
    val dollType: String,
    val dollFace: Int,
    val dollInBox: Int,
    val dollUpBox: Int,
    val dollHelloLottie: LottieCompositionSpec
) {
    BROWN(
        1,
        Brown,
        R.drawable.ic_brown_face,
        R.drawable.ic_doll_brown_box_in,
        R.drawable.ic_doll_brown_box_up,
        LottieCompositionSpec.RawRes(R.raw.brown_hello)
    ),
    GRAY(
        2,
        Gray,
        R.drawable.ic_gray_face,
        R.drawable.ic_doll_gray_box_in,
        R.drawable.ic_doll_gray_box_up,
        LottieCompositionSpec.RawRes(R.raw.gray_hello)
    ),
    WHITE(
        3,
        White,
        R.drawable.ic_white_face,
        R.drawable.ic_doll_white_box_in,
        R.drawable.ic_doll_white_box_up,
        LottieCompositionSpec.RawRes(R.raw.white_hello)
    ),
    RED(
        4,
        Red,
        R.drawable.ic_red_face,
        R.drawable.ic_doll_red_box_in,
        R.drawable.ic_doll_red_box_up,
        LottieCompositionSpec.RawRes(R.raw.red_hello)
    );

    companion object {
        fun getDollFace(dollType: String): Int =
            entries.firstOrNull { it.dollType == dollType }?.dollFace ?: R.drawable.ic_brown_face

        fun getDollBox(dollType: String, isDollSelected: Boolean): Int =
            when (isDollSelected) {
                true -> entries.firstOrNull { it.dollType == dollType }?.dollUpBox ?: -1
                false -> entries.firstOrNull { it.dollType == dollType }?.dollInBox ?: -1
            }

        fun getDollHelloResource(dollType: String): LottieCompositionSpec =
            entries.firstOrNull { it.dollType == dollType }?.dollHelloLottie ?: LottieCompositionSpec.RawRes(R.raw.brown_hello)
    }
}
