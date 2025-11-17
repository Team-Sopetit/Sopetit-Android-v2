package com.sopetit.ui.common.content

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Gray300

@Composable
fun DashedDivider(
    color: Color = Gray300,
    strokeWidth: Dp = 1.dp,
    dashRatio: Float = 0.6f,
    dashCount: Int = 40,
    modifier: Modifier =
        Modifier
            .fillMaxWidth()
            .height(strokeWidth),
) {
    val strokePx = with(LocalDensity.current) { strokeWidth.toPx() }

    Canvas(modifier = modifier) {
        val totalWidth = size.width
        val unitWidth = totalWidth / dashCount
        val dashWidth = unitWidth * dashRatio
        val gapWidth = unitWidth * (1 - dashRatio)

        var startX = 0f
        repeat(dashCount) {
            drawLine(
                color = color,
                start = Offset(startX, 0f),
                end = Offset(startX + dashWidth, 0f),
                strokeWidth = strokePx,
            )
            startX += unitWidth
        }
    }
}
