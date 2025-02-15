package com.sopetit.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Brown50
import com.sopetit.design_system.Gray650
import com.sopetit.design_system.R
import com.sopetit.design_system.SplashBottom
import com.sopetit.splash.component.SplashTitle
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    goToKaKaoLogIn: () -> Unit = {}
) {

    LaunchedEffect(Unit) {
        delay(1500L)
        goToKaKaoLogIn()
    }
    SplashContent(
        splashVersion = 0
    )
}

@Composable
fun SplashContent(
    splashVersion: Int = 0
) {
    when (splashVersion) {
        0 -> SplashItemForVersion(
            colorVersion = 0,
            bottomContent = { SplashFirstBottomContent() }
        )

        1 -> SplashItemForVersion(
            colorVersion = 0,
            bottomContent = { SplashSecondThirdBottomContent() }
        )

        2 -> SplashItemForVersion(
            colorVersion = 1,
            bottomContent = { SplashSecondThirdBottomContent() }
        )

        3 -> SplashItemForVersion(
            colorVersion = 1,
            bottomContent = { SplashFourthBottomContent() }
        )
    }
}

@Composable
fun SplashItemForVersion(
    colorVersion: Int,
    bottomContent: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (colorVersion == 0) Brown50 else Gray650)
    ) {
        SplashTitle(
            contentColor = if (colorVersion == 0) Gray650 else Brown50
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            bottomContent()
        }
    }
}

@Composable
fun SplashFirstBottomContent() {

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

@Composable
fun SplashSecondThirdBottomContent() {
    Box {
        Image(
            painter = painterResource(id = R.drawable.ic_splash_bear2),
            contentDescription = "splash bottom bear",
            modifier = Modifier
                .size(width = 286.dp, height = 499.dp)
                .align(Alignment.BottomStart)
        )
    }
}

@Composable
fun SplashFourthBottomContent() {
    Box {
        Image(
            painter = painterResource(id = R.drawable.ic_splash_bear3),
            contentDescription = "splash bottom bear",
            modifier = Modifier
                .size(width = 420.dp, height = 430.dp)
                .offset(y = 30.dp)
                .align(Alignment.BottomCenter)
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSplash() {
    SplashContent()
}