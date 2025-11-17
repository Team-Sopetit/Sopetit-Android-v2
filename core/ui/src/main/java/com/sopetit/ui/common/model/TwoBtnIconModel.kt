package com.sopetit.ui.common.model

import androidx.compose.ui.graphics.Color

data class TwoBtnIconModel(
    val title: String = "",
    val semiTitle: String = "",
    val leftBtnText: String = "",
    val leftBtnTextColor: Color = Color.Transparent,
    val leftBtnColor: Color = Color.Transparent,
    val rightBtnText: String = "",
    val rightBtnTextColor: Color = Color.Transparent,
    val rightBtnColor: Color = Color.Transparent,
)
