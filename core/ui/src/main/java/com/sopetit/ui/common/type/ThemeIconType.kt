package com.sopetit.ui.common.type

import androidx.compose.ui.graphics.Color
import com.sopetit.designsystem.Blue100
import com.sopetit.designsystem.Blue50
import com.sopetit.designsystem.Green100
import com.sopetit.designsystem.Green50
import com.sopetit.designsystem.Orange100
import com.sopetit.designsystem.Orange50
import com.sopetit.designsystem.Pink100
import com.sopetit.designsystem.Pink50
import com.sopetit.designsystem.R
import com.sopetit.designsystem.Red100
import com.sopetit.designsystem.Red50
import com.sopetit.designsystem.Sky100
import com.sopetit.designsystem.Sky50
import com.sopetit.designsystem.Yellow100
import com.sopetit.designsystem.Yellow50

enum class ThemeIconType(
    val themeId: Int,
    val themeIcon: Int,
    val themeName: String,
    val themeBackgroundImg: Int,
    val themeDetailBackground: Int,
    val themeColor: Color,
    val themeGraphColor: Color,
) {
    ONE(1, R.drawable.ic_theme1, "관계 쌓기", R.drawable.ic_challenge_background1, R.drawable.ic_theme_background1, Pink50, Pink100),
    TWO(2, R.drawable.ic_theme2, "마음 챙김", R.drawable.ic_challenge_background2, R.drawable.ic_theme_background2, Red50, Red100),
    THREE(3, R.drawable.ic_theme3, "통통한 통장", R.drawable.ic_challenge_background3, R.drawable.ic_theme_background3, Orange50, Orange100),
    FOUR(4, R.drawable.ic_theme4, "산뜻한 일상", R.drawable.ic_challenge_background4, R.drawable.ic_theme_background4, Yellow50, Yellow100),
    FIVE(5, R.drawable.ic_theme5, "한 걸음 성장", R.drawable.ic_challenge_background5, R.drawable.ic_theme_background5, Green50, Green100),
    SIX(6, R.drawable.ic_theme6, "건강한 몸", R.drawable.ic_challenge_background6, R.drawable.ic_theme_background6, Sky50, Sky100),
    SEVEN(7, R.drawable.ic_theme7, "나와 친해지기", R.drawable.ic_challenge_background7, R.drawable.ic_theme_background7, Blue50, Blue100),
    ;

    companion object {
        fun getThemeIcon(themeId: Int): Int =
            entries.firstOrNull { it.themeId == themeId }?.themeIcon ?: R.drawable.ic_snackbar_caution

        fun mapThemeIconType(themeId: Int): ThemeIconType =
            when (themeId) {
                1 -> ONE
                2 -> TWO
                3 -> THREE
                4 -> FOUR
                5 -> FIVE
                6 -> SIX
                7 -> SEVEN
                else -> ONE
            }

        fun getThemeDetailBackground(themeId: Int): Int =
            entries.firstOrNull { it.themeId == themeId }?.themeDetailBackground ?: R.drawable.ic_theme_background1

        fun getThemeName(themeId: Int): String =
            entries.firstOrNull { it.themeId == themeId }?.themeName ?: ""

        fun getThemeColor(themeId: Int): Color =
            entries.firstOrNull { it.themeId == themeId }?.themeColor ?: Color.Transparent

        fun getThemeGraphColor(themeId: Int): Color =
            entries.firstOrNull { it.themeId == themeId }?.themeGraphColor ?: Color.Transparent
    }
}
