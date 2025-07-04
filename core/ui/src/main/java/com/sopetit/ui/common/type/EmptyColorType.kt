package com.sopetit.ui.common.type

import androidx.compose.ui.graphics.Color
import com.sopetit.design_system.Gray200
import com.sopetit.design_system.Gray300
import com.sopetit.design_system.Gray400
import com.sopetit.design_system.Gray500

enum class EmptyColorType(
    val color: Color
) {
    ONE(Gray400),
    TWO(Gray300),
    THREE(Gray200),
    FOUR(Gray500)
}