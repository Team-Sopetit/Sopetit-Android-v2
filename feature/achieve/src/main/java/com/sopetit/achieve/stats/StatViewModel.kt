package com.sopetit.achieve.stats

import androidx.lifecycle.viewModelScope
import com.sopetit.domain.entity.response.achieve.AchieveModel
import com.sopetit.domain.usecase.achieve.GetAchievementUseCase
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StatViewModel @Inject constructor(
    private val getAchievementUseCase: GetAchievementUseCase
): BaseViewModel<StatPageState>(
    StatPageState()
) {

    init {
        initGetAchieve()
    }

    private fun initGetAchieve() {
        viewModelScope.launch {
            getAchievementUseCase(Unit).collect{ resultResponse(it, ::onSuccessGetAchieve)}
        }
    }

    private fun onSuccessGetAchieve(data: AchieveModel) {
        Timber.d("[테스트] -> $data")
        updateState(
            uiState.value.copy(
                achieveModel = data
            )
        )
    }
}