package com.sopetit.onboarding.routinechoice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.RoutineChoiceBtn
import com.sopetit.design_system.RoutineChoiceTopOriginalSpeech
import com.sopetit.domain.entity.enums.DollType
import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.ui.common.button.BottomRectangleBtn
import com.sopetit.ui.common.content.TopBearFaceSpeech
import com.sopetit.ui.common.topbar.OnboardingTopBar
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun RoutineChoiceScreen(
    goBackToThemeChoicePage: () -> Unit = {},
    memberModel: SharedFlow<CreateMemberModel> = MutableSharedFlow(),
) {
    val viewModel: RoutineChoiceViewModel = hiltViewModel()
    val uiState: RoutineChoicePageState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(memberModel) {
        memberModel.collect {
            viewModel.getMemberModel(it)
        }
    }

    RoutineChoiceContent(
        onClickBackBtnAction = { goBackToThemeChoicePage() },
        selectedDollType = uiState.memberModel.dollType
    )
}

@Composable
fun RoutineChoiceContent(
    onClickBackBtnAction: () -> Unit = {},
    selectedDollType: DollType = DollType.NONE,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray50)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            OnboardingTopBar(
                page = 4,
                enabledGoBack = true,
                goBack = { onClickBackBtnAction() }
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopBearFaceSpeech(
                    dollType = selectedDollType.value,
                    speechContent = RoutineChoiceTopOriginalSpeech
                )
            }

            BottomRectangleBtn(
                btnTextContent = RoutineChoiceBtn,
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRoutineChoice() {
    RoutineChoiceContent()
}