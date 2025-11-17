package com.sopetit.ui.common.item

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Gray300
import com.sopetit.design_system.Gray650

@Composable
fun PagerIndicator(
    pageNumber: Int,
    currentPage: Int,
) {
    PagerIndicatorContent(
        pageNumber = pageNumber,
        currentPage = currentPage,
    )
}

@Composable
fun PagerIndicatorContent(
    pageNumber: Int,
    currentPage: Int,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        items(pageNumber, key = { it }) {
            PagerIndicatorItem(
                isCurrentPage = it == currentPage,
            )
        }
    }
}

@Composable
fun PagerIndicatorItem(
    isCurrentPage: Boolean,
) {
    Canvas(
        modifier =
            Modifier
                .size(6.dp),
        onDraw = {
            drawCircle(
                color = if (isCurrentPage) Gray650 else Gray300,
                radius = 3.dp.toPx(),
            )
        },
    )
}
