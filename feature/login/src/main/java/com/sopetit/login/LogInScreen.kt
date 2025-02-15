package com.sopetit.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.LogInSpeechContent
import com.sopetit.design_system.R
import com.sopetit.design_system.SoftieTypo

@Composable
fun LogInScreen() {
    LogInContent()
}

@Composable
fun LogInContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray50),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_splash_logo_black),
            contentDescription = "login logo",
            modifier = Modifier
                .padding(top = 143.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = LogInSpeechContent,
                color = Gray0,
                textAlign = TextAlign.Center,
                style = SoftieTypo.caption1,
                modifier = Modifier
                    .paint(painterResource(id = R.drawable.ic_speech))
                    .padding(top = 8.dp, bottom = 17.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_doll_brown_box_in),
                contentDescription = "bear in box",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 44.dp)
                    .size(160.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_login_kakao),
                contentDescription = "login btn",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 104.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLogIn() {
    LogInContent()
}