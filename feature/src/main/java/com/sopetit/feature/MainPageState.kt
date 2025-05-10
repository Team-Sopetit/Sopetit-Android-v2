package com.sopetit.feature

import androidx.compose.ui.unit.IntOffset
import com.sopetit.domain.entity.response.screen.RoutineDetailModel
import com.sopetit.domain.entity.response.screen.TutorialModel
import com.sopetit.ui.base.PageState
import com.sopetit.ui.common.type.BottomSheetType

data class MainPageState(
    val bottomNavType: BottomNavType = BottomNavType.DEFAULT,
    val bottomSheetType: BottomSheetType = BottomSheetType.DEFAULT,
    val tutorials: List<TutorialModel> = emptyList(),
    val routineDetail: RoutineDetailModel = RoutineDetailModel(),
    val isChallengeAchieveShowValid: Boolean = false,
    val isDailyAchieveShowValid: Boolean = false,
    val isTooltipShowValid: Boolean = false,
    val tooltipOffSet: IntOffset = IntOffset.Zero,
    val tooltipTitle: String = "",
    val tooltipContent: String = ""
) : PageState