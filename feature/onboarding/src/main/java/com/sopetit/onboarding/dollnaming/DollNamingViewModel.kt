package com.sopetit.onboarding.dollnaming

import com.airbnb.lottie.compose.LottieCompositionSpec
import com.sopetit.core.enums.DollType
import com.sopetit.design_system.R
import com.sopetit.onboarding.model.DollHelloModel
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DollNamingViewModel @Inject constructor(

) : BaseViewModel<DollNamingPageState>(DollNamingPageState()) {

    fun getSelectedDollType(dollType: DollType) {
        updateState(
            uiState.value.copy(
                selectedDollType = dollType
            )
        )
        Timber.d("[온보딩] dollType -> ${uiState.value.selectedDollType}")
        initSetDollHelloResource(uiState.value.selectedDollType)
    }

    private fun initSetDollHelloResource(selectedDollType: DollType) {
        val dollHelloList: List<DollHelloModel> = listOf(
            DollHelloModel(id = 1, dollType = DollType.BROWN, resource = LottieCompositionSpec.RawRes(R.raw.brown_hello)),
            DollHelloModel(id = 2, dollType = DollType.GRAY, resource = LottieCompositionSpec.RawRes(R.raw.gray_hello)),
            DollHelloModel(id = 3, dollType = DollType.WHITE, resource = LottieCompositionSpec.RawRes(R.raw.white_hello)),
            DollHelloModel(id = 4, dollType = DollType.RED, resource = LottieCompositionSpec.RawRes(R.raw.red_hello))
        )

        updateState(
            uiState.value.copy(
                dollHelloResource = dollHelloList.first { it.dollType == selectedDollType }.resource
            )
        )
    }

    fun onValueChange(newValue: String) {
        updateState(
            uiState.value.copy(
                dollInputName = newValue
            )
        )
    }
}