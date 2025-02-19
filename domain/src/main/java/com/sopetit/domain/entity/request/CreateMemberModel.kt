package com.sopetit.domain.entity.request

import com.sopetit.domain.entity.enums.DollType

data class CreateMemberModel(
    val dollType: DollType = DollType.NONE,
    val dollName: String = "",
    val selectedThemeIdList: List<Int> = emptyList(),
    val selectedRoutineIdList: List<Int> = emptyList()
)