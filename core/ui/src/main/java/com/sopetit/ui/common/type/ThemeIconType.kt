package com.sopetit.ui.common.type

import com.sopetit.design_system.R

enum class ThemeIconType(
    val themeId: Int,
    val themeIcon: Int
) {
    ONE(1, R.drawable.ic_theme1),
    TWO(2, R.drawable.ic_theme2),
    THREE(3, R.drawable.ic_theme3),
    FOUR(4, R.drawable.ic_theme4),
    FIVE(5, R.drawable.ic_theme5),
    SIX(6, R.drawable.ic_theme6),
    SEVEN(7, R.drawable.ic_theme7);

    companion object {
        fun getThemeIcon(themeId: Int): Int =
            entries.firstOrNull { it.themeId == themeId }?.themeIcon ?: -1
    }
}