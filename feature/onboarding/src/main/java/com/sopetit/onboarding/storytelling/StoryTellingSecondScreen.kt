package com.sopetit.onboarding.storytelling

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.R
import com.sopetit.design_system.StoryTellingContent2
import com.sopetit.ui.common.StoryTellingTextContent

@Composable
fun StoryTellingSecondScreen() {

    StoryTellingSecondContent()

}

@Composable
fun StoryTellingSecondContent() {
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
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_storytelling_girl2),
                    contentDescription = "story telling girl",
                    modifier = Modifier
                        .size(width = 210.dp, height = 360.dp)
                        .align(Alignment.TopStart)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_box_closed_split),
                    contentDescription = "story telling closed box",
                    modifier = Modifier
                        .size(width = 141.dp, height = 120.dp)
                        .align(Alignment.BottomEnd)
                        .offset(x = 20.dp)
                )
            }

            Spacer(modifier = Modifier.height(63.dp))

            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(bottom = 107.dp)
            ) {
                StoryTellingTextContent(
                    storyContent = StoryTellingContent2
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewStoryTellingSecond() {
    StoryTellingSecondContent()
}