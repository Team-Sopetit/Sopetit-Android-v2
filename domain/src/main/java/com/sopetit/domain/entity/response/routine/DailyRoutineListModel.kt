package com.sopetit.domain.entity.response.routine

data class DailyRoutineListModel(
    val themeId: Int = -1,
    val routines: List<DailyRoutineListItemModel> = emptyList(),
)
