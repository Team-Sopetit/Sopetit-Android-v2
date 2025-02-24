package com.sopetit.feature

import com.sopetit.design_system.R
import com.sopetit.navigation.NavRoutes

enum class BottomNavType(
    val navName: String,
    val navIconOn: Int,
    val navIconOff: Int,
    val destination: String
) {
    HOME("홈", R.drawable.ic_bottom_home_on, R.drawable.ic_bottom_home_off, NavRoutes.HomeScreen.route),
    PROGRESS("진행 중", R.drawable.ic_bottom_progress_on, R.drawable.ic_bottom_progress_off, NavRoutes.ProgressGraph.route),
    ACHIEVE("달성도", R.drawable.ic_bottom_achieve_on, R.drawable.ic_bottom_achieve_off, NavRoutes.AchieveScreen.route),
    DEFAULT("", -1, -1, "");

    companion object {
        fun getBottomNavIcon(navType: BottomNavType, isSelected: Boolean): Int =
            when (isSelected) {
                true -> entries.firstOrNull { it == navType }?.navIconOn ?: -1
                false -> entries.firstOrNull { it == navType }?.navIconOff ?: -1
            }

        fun getDestination(navType: BottomNavType): String =
            entries.firstOrNull { it == navType }?.destination ?: ""
    }
}