package com.sopetit.achieve.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sopetit.design_system.Gray50

@Composable
fun StatScreen() {
    StatContent()
}

@Composable
fun StatContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray50)
    ) {
        Text(text = "통계")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewStat() {
    StatContent()
}