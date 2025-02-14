package com.sopetit.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Brown50
import com.sopetit.design_system.Gray650
import com.sopetit.design_system.R
import com.sopetit.design_system.SoftieTypo
import com.sopetit.design_system.SplashBottom
import com.sopetit.design_system.SplashSemiTitle

@Composable
fun SplashScreen() {
    SplashContent()
}

@Composable
fun SplashContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brown50)
    ) {
        SplashTitle()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            SplashBottomContent()
        }
    }
}

@Composable
fun SplashTitle() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_splash_logo_black),
            contentDescription = "splash logo",
            modifier = Modifier
                .padding(top = 143.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = SplashSemiTitle,
            modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally),
            color = Gray650,
            style = SoftieTypo.body1
        )
    }
}

@Composable
fun SplashBottomContent() {

    Box {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(225.dp)
                .background(SplashBottom)
                .align(Alignment.BottomCenter)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_splash_bear1),
            contentDescription = "splash bottom bear",
            modifier = Modifier
                .size(width = 319.dp, height = 278.dp)
                .offset(y = (-79).dp)
                .align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSplash() {
    SplashContent()
}