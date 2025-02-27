package com.sopetit.achieve

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sopetit.design_system.Gray50

@Composable
fun AchieveScreen() {
    AchieveContent()
}

@Composable
fun AchieveContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray50)
    ) {
        Text(
            text = "달성도"
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAchieve() {
    AchieveContent()
}