package com.sopetit.design_system

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val fontFamily = FontFamily(
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.omyu_pretty, FontWeight.Normal)
)

object SoftieTypo {
    val head1: TextStyle = TextStyle(
        fontSize = 20.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 56.sp
    )

    val head2: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 28.sp
    )

    val head3: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp
    )

    val head4: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 20.sp
    )

    val body1: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium,
        lineHeight = 24.sp
    )

    val body2: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium,
        lineHeight = 20.sp
    )

    val caption1: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium,
        lineHeight = 18.sp
    )

    val caption2: TextStyle = TextStyle(
        fontSize = 10.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium,
        lineHeight = 12.sp
    )

    val bubble1: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
    )

    val bubble2: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
    )
}