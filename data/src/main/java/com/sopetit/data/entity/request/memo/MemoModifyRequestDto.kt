package com.sopetit.data.entity.request.memo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemoModifyRequestDto (
    @SerialName("content")
    val content: String = ""
)