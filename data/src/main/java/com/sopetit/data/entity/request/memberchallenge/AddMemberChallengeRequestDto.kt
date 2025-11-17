package com.sopetit.data.entity.request.memberchallenge

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddMemberChallengeRequestDto(
    @SerialName("challengeId")
    val challengeId: Int,
)
