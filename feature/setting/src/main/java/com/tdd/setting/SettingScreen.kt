package com.tdd.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.SettingTitle
import com.sopetit.ui.common.topbar.TopBarContent

@Composable
fun SettingScreen(
    goBackPage: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    SettingContent(
        interactionSource = interactionSource,
        onClickBackBtn = { goBackPage() }
    )
}

@Composable
fun SettingContent(
    interactionSource: MutableInteractionSource,
    onClickBackBtn: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray0)
    ) {
        TopBarContent(
            content = SettingTitle,
            interactionSource = interactionSource,
            onClickIcon = onClickBackBtn
        )
    }
}