package com.sopetit.data.entity.request.member

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostFcmRequestDto(
    @SerialName("fcmToken")
    val fcmToken: String = "",
)
