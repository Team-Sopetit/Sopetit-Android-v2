package com.sopetit.onboarding.storytelling

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.R
import com.sopetit.design_system.StoryTellingContent1
import com.sopetit.ui.common.StoryTellingText


@Composable
fun StoryTellingFirstScreen(
    goToSecondStoryPage: () -> Unit = {}
) {

    StoryTellingFirstContent(
        onClickContent = { goToSecondStoryPage() }
    )
}

@Composable
fun StoryTellingFirstContent(
    onClickContent: () -> Unit = {}
) {
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
                    .wrapContentSize()
                    .padding(start = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_storytelling_girl1),
                    contentDescription = "story telling girl",
                    modifier = Modifier
                        .size(width = 210.dp, height = 360.dp)
                        .align(Alignment.TopStart)
                )
            }

            StoryTellingText(
                storyContent = StoryTellingContent1,
                onClickAction = onClickContent
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewStoryTelling() {
    StoryTellingFirstContent()
}