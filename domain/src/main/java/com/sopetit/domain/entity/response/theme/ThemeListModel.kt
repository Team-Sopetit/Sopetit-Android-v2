package com.sopetit.domain.entity.response.theme

data class ThemeListModel (
    val themes: List<ThemeListItemModel> = emptyList()
)