package com.sopetit.onboarding.dollnaming

import com.airbnb.lottie.compose.LottieCompositionSpec
import com.sopetit.core.enums.DollType
import com.sopetit.design_system.R
import com.sopetit.onboarding.model.DollHelloModel
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DollNamingViewModel @Inject constructor(

) : BaseViewModel<DollNamingPageState>(DollNamingPageState()) {

    init {
        initSetDollHelloList()
    }

    private fun initSetDollHelloList() {
        updateState(
            uiState.value.copy(
                dollHelloList = listOf(
                    DollHelloModel(
                        id = 1,
                        dollType = DollType.BROWN,
                        resource = LottieCompositionSpec.RawRes(R.raw.brown_hello)
                    ),
                    DollHelloModel(
                        id = 2,
                        dollType = DollType.GRAY,
                        resource = LottieCompositionSpec.RawRes(R.raw.gray_hello)
                    ),
                    DollHelloModel(
                        id = 3,
                        dollType = DollType.WHITE,
                        resource = LottieCompositionSpec.RawRes(R.raw.white_hello)
                    ),
                    DollHelloModel(
                        id = 4,
                        dollType = DollType.RED,
                        resource = LottieCompositionSpec.RawRes(R.raw.red_hello)
                    )
                )
            )
        )
    }
}