package com.sopetit.data.entity.response.memberchallenge

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddMemberChallengeResponseDto(
    @SerialName("memberChallengeId")
    val memberChallengeId: Int = 0,
)
