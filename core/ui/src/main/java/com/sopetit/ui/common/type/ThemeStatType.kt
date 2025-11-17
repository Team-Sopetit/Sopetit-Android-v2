package com.sopetit.ui.common.type

import com.sopetit.designsystem.R

enum class ThemeStatType(
    val themeId: Int,
    val themeStatBackground: Int,
    val statTitle: String,
    val statContent: String,
) {
    ONE(1, R.drawable.ic_stat_background1, "따뜻한 솜뭉치", "다정다감해요"),
    TWO(2, R.drawable.ic_stat_background2, "편안한 솜뭉치", "차분하고 성숙해요"),
    THREE(3, R.drawable.ic_stat_background3, "똑똑한 솜뭉치", "꼼꼼하고 현실적이에요"),
    FOUR(4, R.drawable.ic_stat_background4, "에너지 솜뭉치", "밝고 긍정적이에요"),
    FIVE(5, R.drawable.ic_stat_background5, "느긋한 솜뭉치", "차분하고 여유로워요"),
    SIX(6, R.drawable.ic_stat_background6, "활력의 솜뭉치", "활발하고 힘이 넘쳐요"),
    SEVEN(7, R.drawable.ic_stat_background7, "다정한 솜뭉치", "섬세하고 온화해요"),
    ;

    companion object {
        fun getStatBackground(themeId: Int): Int =
            entries.firstOrNull { it.themeId == themeId }?.themeStatBackground ?: -1

        fun getStatTitle(themeId: Int): String =
            entries.firstOrNull { it.themeId == themeId }?.statTitle ?: ""

        fun getStatContent(themeId: Int): String =
            entries.firstOrNull { it.themeId == themeId }?.statContent ?: ""
    }
}
