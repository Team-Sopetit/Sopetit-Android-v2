package com.sopetit.feature

import androidx.compose.ui.unit.IntOffset
import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.domain.entity.response.screen.RoutineDetailModel
import com.sopetit.domain.entity.response.screen.TutorialModel
import com.sopetit.navigation.NavRoutes
import com.sopetit.ui.base.BaseViewModel
import com.sopetit.ui.common.type.BottomSheetType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

) : BaseViewModel<MainPageState>(
    MainPageState()
) {

    val memberModel = MutableSharedFlow<CreateMemberModel>(replay = 1)
    val isTutorialValid = MutableSharedFlow<Boolean>(replay = 1)
    val deleteRoutineId = MutableSharedFlow<RoutineDetailModel>()

    fun setBottomNavType(route: String?) {
        val type = when (route) {
            NavRoutes.HomeScreen.route -> {
                BottomNavType.HOME
            }

            NavRoutes.ProgressScreen.route -> {
                BottomNavType.PROGRESS
            }

            NavRoutes.AchieveScreen.route -> {
                BottomNavType.ACHIEVE
            }

            else -> {
                BottomNavType.DEFAULT
            }
        }

        updateBottomNav(type)
    }

    private fun updateBottomNav(type: BottomNavType) {
        updateState(
            uiState.value.copy(
                bottomNavType = type
            )
        )
    }

    fun setTutorials(tutorials: List<TutorialModel>) {
        updateState(
            uiState.value.copy(
                tutorials = tutorials,
                bottomSheetType = BottomSheetType.TUTORIAL
            )
        )
    }

    fun setRoutineDetail(routine: RoutineDetailModel) {
        updateState(
            uiState.value.copy(
                routineDetail = routine,
                bottomSheetType = BottomSheetType.ROUTINE
            )
        )
    }

    fun updateChallengeAchieve(isValid: Boolean) {
        updateState(
            uiState.value.copy(
                isChallengeAchieveShowValid = isValid
            )
        )
    }

    fun updateDailyAchieve(isValid: Boolean) {
        updateState(
            uiState.value.copy(
                isDailyAchieveShowValid = isValid
            )
        )
    }

    fun initSetTooltip(isValid: Boolean, intOffset: IntOffset, title: String, content: String) {
        updateState(
            uiState.value.copy(
                isTooltipShowValid = isValid,
                tooltipOffSet = intOffset,
                tooltipTitle = title,
                tooltipContent = content
            )
        )
    }

    fun updateTooltipState(isValid: Boolean) {
        updateState(
            uiState.value.copy(
                isTooltipShowValid = isValid,
            )
        )
    }
}