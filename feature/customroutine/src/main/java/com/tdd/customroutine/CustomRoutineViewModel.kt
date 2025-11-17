package com.tdd.customroutine

import androidx.lifecycle.viewModelScope
import com.sopetit.designsystem.TimeAM
import com.sopetit.designsystem.TimeMinuteFifty
import com.sopetit.designsystem.TimeMinuteForty
import com.sopetit.designsystem.TimeMinuteHalf
import com.sopetit.designsystem.TimeMinuteTen
import com.sopetit.designsystem.TimeMinuteTwenty
import com.sopetit.designsystem.TimeMinuteZero
import com.sopetit.domain.entity.enums.CustomScreenType
import com.sopetit.domain.entity.request.customroutine.CustomRoutineRequestModel
import com.sopetit.domain.entity.request.customroutine.ModifyCustomRoutineRequestModel
import com.sopetit.domain.entity.response.customroutine.CustomRoutineModel
import com.sopetit.domain.entity.response.screen.ModifyRoutineModel
import com.sopetit.domain.usecase.customroutine.ModifyCustomRoutineUseCase
import com.sopetit.domain.usecase.customroutine.PostCreateCustomRoutineUseCase
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomRoutineViewModel
    @Inject
    constructor(
        private val postCreateCustomRoutineUseCase: PostCreateCustomRoutineUseCase,
        private val modifyCustomRoutineUseCase: ModifyCustomRoutineUseCase,
    ) : BaseViewModel<CustomRoutinePageState>(
            CustomRoutinePageState(),
        ) {
        fun setModifyRoutine(modifyRoutineModel: ModifyRoutineModel) {
            updateState(
                uiState.value.copy(
                    selectedThemeId = modifyRoutineModel.themeId,
                    routineWriteInput = modifyRoutineModel.content,
                    modifyType = modifyRoutineModel.routineType,
                    customScreenType = modifyRoutineModel.customScreenType,
                    modifyRoutine = modifyRoutineModel,
                ),
            )
        }

        fun setSelectedTheme(themeId: Int) {
            updateState(
                uiState.value.copy(
                    selectedThemeId = themeId,
                ),
            )
        }

        fun onRoutineValueChange(newValue: String) {
            updateState(
                uiState.value.copy(
                    routineWriteInput = newValue,
                ),
            )
        }

        fun updateAlarmActivated(isActivated: Boolean) {
            updateState(
                uiState.value.copy(
                    isAlarmActivated = isActivated,
                ),
            )
        }

        fun setCustomCreateOrModify(alarmTime: String) {
            when (uiState.value.customScreenType) {
                CustomScreenType.Create -> createCustomRoutine(alarmTime)
                CustomScreenType.Modify -> modifyCustomRoutine(alarmTime)
            }
        }

        private fun createCustomRoutine(alarmTime: String) {
            viewModelScope.launch {
                postCreateCustomRoutineUseCase(
                    CustomRoutineRequestModel(
                        uiState.value.routineWriteInput,
                        uiState.value.selectedThemeId,
                        alarmTime,
                    ),
                ).collect { resultResponse(it, ::onSuccessCustomRoutine) }
            }
        }

        private fun modifyCustomRoutine(alarmTime: String) {
            viewModelScope.launch {
                modifyCustomRoutineUseCase(
                    ModifyCustomRoutineRequestModel(
                        uiState.value.modifyRoutine.routineId,
                        CustomRoutineRequestModel(
                            uiState.value.routineWriteInput,
                            uiState.value.selectedThemeId,
                            alarmTime,
                        ),
                    ),
                ).collect { resultResponse(it, ::onSuccessCustomRoutine) }
            }
        }

        private fun onSuccessCustomRoutine(data: CustomRoutineModel) {
            emitEventFlow(CustomRoutineEvent.GoToProgressPage)
        }

        fun convertTimeState(time: String): Int {
            return when (time == TimeAM) {
                true -> 0
                false -> 1
            }
        }

        fun convertMinuteState(time: String): Int {
            return when (time) {
                TimeMinuteZero -> 0
                TimeMinuteTen -> 1
                TimeMinuteTwenty -> 2
                TimeMinuteHalf -> 3
                TimeMinuteForty -> 4
                TimeMinuteFifty -> 5
                else -> 0
            }
        }
    }
