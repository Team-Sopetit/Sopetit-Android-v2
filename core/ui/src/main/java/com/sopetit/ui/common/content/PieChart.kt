package com.sopetit.ui.common.content

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sopetit.design_system.Gray50

@Composable
fun PieChart(
    proportions: List<Float>,
    colors: List<Color>,
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier) {
        val diameter = size.minDimension
        var startAngle = -90f

        proportions.forEachIndexed { index, proportion ->
            val sweepAngle = proportion * 360f
            drawArc(
                color = colors[index],
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = true,
            )
            startAngle += sweepAngle
        }

        drawCircle(
            color = Gray50,
            radius = diameter * 0.5f / 2,
            center = center,
        )
    }
}
