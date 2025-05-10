package com.sopetit.domain.entity.response.routine

data class ChallengeChangeModel (
    val hasChallenge: ChallengeItemModel = ChallengeItemModel(),
    val changeChallenge: ChallengeItemModel = ChallengeItemModel()
)