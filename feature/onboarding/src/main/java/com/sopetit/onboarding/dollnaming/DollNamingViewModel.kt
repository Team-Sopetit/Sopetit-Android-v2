package com.sopetit.onboarding.dollnaming

import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.ui.base.BaseViewModel
import com.sopetit.ui.common.type.BearType
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DollNamingViewModel @Inject constructor(

) : BaseViewModel<DollNamingPageState>(DollNamingPageState()) {

    fun getMemberModel(memberModel: CreateMemberModel) {
        updateState(
            uiState.value.copy(
                memberModel = memberModel,
                dollHelloResource = BearType.getDollHelloResource(memberModel.dollType.value)
            )
        )
        Timber.d("[온보딩] (dollNaming) member -> ${uiState.value.memberModel}")
    }

    fun updateMemberModel() = CreateMemberModel(
        dollType = uiState.value.memberModel.dollType,
        dollName = uiState.value.dollInputName
    )

    fun onValueChange(newValue: String) {
        updateState(
            uiState.value.copy(
                dollInputName = newValue
            )
        )
    }
}