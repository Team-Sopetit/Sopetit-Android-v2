package com.sopetit.onboarding.dolltype

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.DollTypeChoiceBtn
import com.sopetit.design_system.DollTypeChoiceSemiTitle
import com.sopetit.design_system.DollTypeChoiceTitle
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.Gray500
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.SoftieTypo
import com.sopetit.ui.common.item.BottomRectangleBtn
import com.sopetit.ui.common.topbar.OnboardingTopBar

@Composable
fun DollTypeChoiceScreen() {

    DollTypeChoiceContent()
}

@Composable
fun DollTypeChoiceContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray50)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            OnboardingTopBar(page = 2)

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = DollTypeChoiceTitle,
                    style = SoftieTypo.head1,
                    color = Gray700,
                    modifier = Modifier
                        .padding(top = 28.dp)
                )

                Text(
                    text = DollTypeChoiceSemiTitle,
                    style = SoftieTypo.body2,
                    color = Gray500,
                    modifier = Modifier
                        .padding(top = 4.dp)
                )
            }

            BottomRectangleBtn(btnTextContent = DollTypeChoiceBtn)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDollTypeChoice() {
    DollTypeChoiceContent()
}