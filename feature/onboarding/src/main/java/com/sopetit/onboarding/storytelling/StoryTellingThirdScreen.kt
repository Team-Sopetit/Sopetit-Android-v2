package com.sopetit.onboarding.storytelling

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.R
import com.sopetit.design_system.StoryTellingContent3
import com.sopetit.ui.common.StoryTellingText

@Composable
fun StoryTellingThirdScreen() {
    StoryTellingThirdContent()
}

@Composable
fun StoryTellingThirdContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray50)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_door),
                    contentDescription = "story telling girl",
                    modifier = Modifier
                        .size(width = 211.dp, height = 390.dp)
                        .padding(bottom = 40.dp)
                        .align(Alignment.Center)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_box_closed),
                    contentDescription = "story telling closed box",
                    modifier = Modifier
                        .size(width = 141.dp, height = 120.dp)
                        .align(Alignment.BottomCenter)
                )
            }

            StoryTellingText(
                storyContent = StoryTellingContent3
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewStoryTellingThird() {
    StoryTellingThirdContent()
}