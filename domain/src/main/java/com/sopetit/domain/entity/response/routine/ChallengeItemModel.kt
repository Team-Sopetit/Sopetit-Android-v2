package com.sopetit.domain.entity.response.routine

data class ChallengeItemModel(
    val challengeId: Int = 0,
    val content: String = "",
    val description: String = "",
    val requiredTime: String = "",
    val place: String = "",
    val hasRoutine: Boolean = false,
)
