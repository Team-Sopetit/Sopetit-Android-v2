package com.sopetit.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.R
import com.sopetit.design_system.SoftieTypo

@Composable
fun StoryTellingText(
    storyContent: String,
    onClickAction: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }

    StoryTellingTextContent(
        storyContent = storyContent,
        onClickAction = onClickAction,
        interactionSource = interactionSource
    )
}

@Composable
fun StoryTellingTextContent(
    storyContent: String,
    onClickAction: () -> Unit = {},
    interactionSource: MutableInteractionSource = MutableInteractionSource()
) {

    Spacer(modifier = Modifier.height(63.dp))

    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(bottom = 107.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(12))
                .background(Gray0)
                .wrapContentHeight()
                .clickable(
                    indication = null,
                    interactionSource = interactionSource,
                    onClick = { onClickAction() }
                )
        ) {
            Text(
                text = storyContent,
                style = SoftieTypo.bubble1,
                color = Gray700,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 23.dp)
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_btn_next),
                contentDescription = "next btn",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 23.dp)
                    .size(14.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewStoryTellingContent() {
    StoryTellingTextContent(storyContent = "test")
}