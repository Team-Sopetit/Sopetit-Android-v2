package com.sopetit.ui.common.type

import com.sopetit.design_system.R

enum class BearFaceType(
    val dollType: String,
    val dollFace: Int,
) {
    BROWN("BROWN", R.drawable.ic_brown_face),
    GRAY("GRAY", R.drawable.ic_gray_face),
    WHITE("WHITE", R.drawable.ic_white_face),
    RED("RED", R.drawable.ic_red_face),
    NONE("NONE", R.drawable.ic_brown_face);

    companion object {
        fun getDollFace(dollType: String): Int =
            entries.firstOrNull { it.dollType == dollType }?.dollFace ?: -1
    }
}
