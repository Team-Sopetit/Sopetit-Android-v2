package com.sopetit.onboarding.routinechoice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.RoutineChoiceBtn
import com.sopetit.ui.common.button.BottomRectangleBtn
import com.sopetit.ui.common.topbar.OnboardingTopBar

@Composable
fun RoutineChoiceScreen(
    goBackToThemeChoicePage: () -> Unit = {}
) {
    RoutineChoiceContent(
        onClickBackBtnAction = { goBackToThemeChoicePage() }
    )
}

@Composable
fun RoutineChoiceContent(
    onClickBackBtnAction: () -> Unit = {},
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
                //
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