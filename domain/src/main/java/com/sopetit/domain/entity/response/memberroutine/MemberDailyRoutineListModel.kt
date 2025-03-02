package com.sopetit.domain.entity.response.memberroutine

data class MemberDailyRoutineListModel(
    val themeId: Int = -1,
    val themeName: String = "",
    val routines: List<MemberDailyRoutineListItemModel> = emptyList(),
)