package com.sopetit.ui.common.type

import androidx.compose.ui.graphics.Color
import com.sopetit.design_system.Blue50
import com.sopetit.design_system.Green50
import com.sopetit.design_system.Orange50
import com.sopetit.design_system.Pink50
import com.sopetit.design_system.R
import com.sopetit.design_system.Red50
import com.sopetit.design_system.Sky50
import com.sopetit.design_system.Yellow50

enum class ThemeIconType(
    val themeId: Int,
    val themeIcon: Int,
    val themeBackgroundImg: Int,
    val themeColor: Color,
) {
    ONE(1, R.drawable.ic_theme1, R.drawable.ic_challenge_background1, Pink50),
    TWO(2, R.drawable.ic_theme2, R.drawable.ic_challenge_background2, Red50),
    THREE(3, R.drawable.ic_theme3, R.drawable.ic_challenge_background3, Orange50),
    FOUR(4, R.drawable.ic_theme4, R.drawable.ic_challenge_background4, Yellow50),
    FIVE(5, R.drawable.ic_theme5, R.drawable.ic_challenge_background5, Green50),
    SIX(6, R.drawable.ic_theme6, R.drawable.ic_challenge_background6, Sky50),
    SEVEN(7, R.drawable.ic_theme7, R.drawable.ic_challenge_background7, Blue50);

    companion object {
        fun getThemeIcon(themeId: Int): Int =
            entries.firstOrNull { it.themeId == themeId }?.themeIcon ?: -1

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
    }
}