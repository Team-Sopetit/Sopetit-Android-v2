package com.sopetit.ui.common.type

import androidx.compose.ui.graphics.Color
import com.sopetit.designsystem.Gray200
import com.sopetit.designsystem.Gray300
import com.sopetit.designsystem.Gray400
import com.sopetit.designsystem.Gray500

enum class EmptyColorType(
    val color: Color,
) {
    ONE(Gray400),
    TWO(Gray300),
    THREE(Gray200),
    FOUR(Gray500),
}
