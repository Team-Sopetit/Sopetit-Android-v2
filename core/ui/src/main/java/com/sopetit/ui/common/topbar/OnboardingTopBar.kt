package com.sopetit.ui.common.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Gray200
import com.sopetit.design_system.Gray650
import com.sopetit.design_system.R

@Composable
fun OnboardingTopBar(
    page: Int = 0,
    enabledGoBack: Boolean = false,
    goBack: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }

    OnboardingTopBarContent(
        boxIndex = page - 1,
        enabledGoBack = enabledGoBack,
        onClickAction = { goBack() },
        interactionSource = interactionSource
    )
}

@Composable
fun OnboardingTopBarContent(
    boxIndex: Int = 0,
    enabledGoBack: Boolean = false,
    onClickAction: () -> Unit = {},
    interactionSource: MutableInteractionSource = MutableInteractionSource()
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        if (enabledGoBack) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "back",
                modifier = Modifier
                    .padding(start = 20.dp)
                    .padding(vertical = 14.dp)
                    .size(28.dp)
                    .clickable(
                        indication = null,
                        interactionSource = interactionSource,
                        onClick = { onClickAction() }
                    )
            )
            Spacer(modifier = Modifier.height(4.dp))
        } else {
            Spacer(modifier = Modifier.height(24.dp))
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(4, key = { it }) { index ->
                    OnboardingTopBarItem(
                        boxActivation = (index <= boxIndex)
                    )
                }
            }
        }
    }
}

@Composable
fun OnboardingTopBarItem(
    boxActivation: Boolean = false
) {
    Box(
        modifier = Modifier
            .size(height = 5.dp, width = 80.dp)
            .clip(RoundedCornerShape(99))
            .background(if (boxActivation) Gray650 else Gray200)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewOnboardingTopBar() {
    OnboardingTopBarContent()
}