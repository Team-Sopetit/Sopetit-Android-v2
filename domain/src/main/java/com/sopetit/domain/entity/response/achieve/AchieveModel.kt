package com.sopetit.domain.entity.response.achieve

data class AchieveModel(
    val achievedCount: Int = 0,
    val themes: List<AchieveThemeItemModel> = emptyList(),
)