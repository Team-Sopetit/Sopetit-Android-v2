package com.sopetit.data.entity.response.memo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemoWriteResponseDto (
    @SerialName("memoId")
    val memoId: Int = 0
)