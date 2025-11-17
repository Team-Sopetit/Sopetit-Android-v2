package com.sopetit.data.entity.request.memo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemoWriteRequestDto(
    @SerialName("achievedDate")
    val achievedDate: String = "",
    @SerialName("content")
    val content: String = "",
)
