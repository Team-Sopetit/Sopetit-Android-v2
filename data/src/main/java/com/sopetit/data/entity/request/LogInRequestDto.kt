package com.sopetit.data.entity.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LogInRequestDto(
    @SerialName("socialType")
    val socialType: String
)