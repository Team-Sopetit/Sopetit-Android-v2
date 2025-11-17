package com.sopetit.feature

import androidx.compose.ui.unit.IntOffset
import com.sopetit.domain.entity.response.memo.MemoActionModel
import com.sopetit.domain.entity.response.routine.ChallengeChangeModel
import com.sopetit.domain.entity.response.screen.ModifyRoutineModel
import com.sopetit.domain.entity.response.screen.RoutineDetailModel
import com.sopetit.domain.entity.response.screen.TutorialModel
import com.sopetit.ui.base.PageState
import com.sopetit.ui.common.model.TwoBtnDialogModel
import com.sopetit.ui.common.model.TwoBtnIconModel
import com.sopetit.ui.common.type.BottomSheetType
import com.sopetit.ui.common.type.TwoBtnBottomSheetType

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
    val tooltipContent: String = "",
    val challengeChangeModel: ChallengeChangeModel = ChallengeChangeModel(),
    val memoActionModel: MemoActionModel = MemoActionModel(),
    val twoBtnType: TwoBtnBottomSheetType = TwoBtnBottomSheetType.MemoWrite,
    val modifyRoutine: ModifyRoutineModel = ModifyRoutineModel(),
    val twoBtnIconModel: TwoBtnIconModel = TwoBtnIconModel(),
    val twoBtnDialogModel: TwoBtnDialogModel = TwoBtnDialogModel(),
) : PageState
