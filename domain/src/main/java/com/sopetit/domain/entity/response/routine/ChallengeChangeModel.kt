package com.sopetit.domain.entity.response.routine

import com.sopetit.domain.entity.response.memberchallenge.MemberChallengeModel

data class ChallengeChangeModel(
    val hasChallenge: MemberChallengeModel = MemberChallengeModel(),
    val changeChallenge: MemberChallengeModel = MemberChallengeModel(),
)
