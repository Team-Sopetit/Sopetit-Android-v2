package com.sopetit.onboarding.model

import com.sopetit.domain.entity.enums.DollType

data class DollTypeModel (
    val id: Long = -1,
    val dollType: DollType = DollType.NONE,
    val dollInBox: Int = 0,
    val dollUpBox: Int = 0
)