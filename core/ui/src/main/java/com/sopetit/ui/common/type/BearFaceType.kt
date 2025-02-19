package com.sopetit.ui.common.type

import com.sopetit.design_system.R

enum class BearFaceType(
    val id: Int,
    val dollType: String,
    val dollFace: Int,
    val dollInBox: Int,
    val dollUpBox: Int
) {
//    NONE(
//        0,
//        "NONE",
//        R.drawable.ic_brown_face,
//        R.drawable.ic_doll_brown_box_in,
//        R.drawable.ic_doll_brown_box_up
//    ),
    BROWN(
        1,
        "BROWN",
        R.drawable.ic_brown_face,
        R.drawable.ic_doll_brown_box_in,
        R.drawable.ic_doll_brown_box_up
    ),
    GRAY(
        2,
        "GRAY",
        R.drawable.ic_gray_face,
        R.drawable.ic_doll_gray_box_in,
        R.drawable.ic_doll_gray_box_up
    ),
    WHITE(
        3,
        "WHITE",
        R.drawable.ic_white_face,
        R.drawable.ic_doll_white_box_in,
        R.drawable.ic_doll_white_box_up
    ),
    RED(
        4,
        "RED",
        R.drawable.ic_red_face,
        R.drawable.ic_doll_red_box_in,
        R.drawable.ic_doll_red_box_up
    );

    companion object {
        fun getDollFace(dollType: String): Int =
            entries.firstOrNull { it.dollType == dollType }?.dollFace ?: -1

        fun getDollBox(dollType: String, isDollSelected: Boolean): Int =
            when (isDollSelected) {
                true -> entries.firstOrNull { it.dollType == dollType }?.dollUpBox ?: -1
                false -> entries.firstOrNull { it.dollType == dollType }?.dollInBox ?: -1
            }
    }
}
