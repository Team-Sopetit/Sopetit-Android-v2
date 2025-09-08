package com.sopetit.data.entity.response.member

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CottonResponseDto (
    @SerialName("cottonCount")
    val cottonCount: Int
)