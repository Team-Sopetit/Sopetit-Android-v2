package com.sopetit.splash.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopetit.designsystem.Gray650
import com.sopetit.designsystem.R
import com.sopetit.designsystem.SoftieTypo
import com.sopetit.designsystem.SplashSemiTitle

@Composable
fun SplashTitle(
    contentColor: Color = Gray650
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_splash_logo_black),
            contentDescription = "splash logo",
            colorFilter = ColorFilter.tint(contentColor),
            modifier = Modifier
                .padding(top = 143.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = SplashSemiTitle,
            modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally),
            color = contentColor,
            style = SoftieTypo.body1
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSplashTitle() {
    SplashTitle()
}