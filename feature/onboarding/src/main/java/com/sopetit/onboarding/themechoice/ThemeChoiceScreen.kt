package com.sopetit.onboarding.themechoice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.ThemeChoiceBtn
import com.sopetit.ui.common.item.BottomRectangleBtn
import com.sopetit.ui.common.topbar.OnboardingTopBar

@Composable
fun ThemeChoiceScreen() {
    ThemeChoiceContent()
}

@Composable
fun ThemeChoiceContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray50)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            OnboardingTopBar(page = 3)

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //
            }

            BottomRectangleBtn(
                btnTextContent = ThemeChoiceBtn
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewThemeChoice() {
    ThemeChoiceContent()
}