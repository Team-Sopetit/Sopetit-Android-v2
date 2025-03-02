package com.sopetit.domain.entity.response.memberchallenge

import com.sopetit.domain.entity.response.theme.ChallengeThemeItemModel

data class MemberChallengeModel(
    val memberChallengeId: Int = -1,
    val theme: ChallengeThemeItemModel = ChallengeThemeItemModel(),
    val content: String = "",
    val description: String = "",
    val place: String = "",
    val timeTaken: String = "",
)