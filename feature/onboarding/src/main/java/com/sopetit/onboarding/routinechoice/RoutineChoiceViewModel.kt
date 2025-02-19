package com.sopetit.onboarding.routinechoice

import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RoutineChoiceViewModel @Inject constructor(

): BaseViewModel<RoutineChoicePageState>(
    RoutineChoicePageState()
) {

    fun getMemberModel(memberModel: CreateMemberModel) {
        updateState(
            uiState.value.copy(
                memberModel = memberModel
            )
        )
        Timber.d("[온보딩] (routineChoice) member -> ${uiState.value.memberModel}")
    }
}