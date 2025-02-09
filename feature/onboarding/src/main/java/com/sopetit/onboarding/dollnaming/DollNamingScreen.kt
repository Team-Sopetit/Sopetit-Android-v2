package com.sopetit.onboarding.dollnaming

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopetit.design_system.DollNamingBtn
import com.sopetit.design_system.DollNamingSemiTitle
import com.sopetit.design_system.DollNamingTitle
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.Gray500
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.SoftieTypo
import com.sopetit.ui.common.item.BottomRectangleBtn
import com.sopetit.ui.common.topbar.OnboardingTopBar

@Composable
fun DollNamingScreen() {
    DollNamingContent()
}

@Composable
fun DollNamingContent() {
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
                    text = DollNamingTitle,
                    style = SoftieTypo.head1,
                    color = Gray700,
                    modifier = Modifier
                        .padding(top = 28.dp),
                    textAlign = TextAlign.Center,
                    lineHeight = 25.sp
                )

                Text(
                    text = DollNamingSemiTitle,
                    style = SoftieTypo.body2,
                    color = Gray500,
                    modifier = Modifier
                        .padding(top = 4.dp)
                )
            }

            BottomRectangleBtn(
                btnTextContent = DollNamingBtn
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDollNaming() {
    DollNamingContent()
}