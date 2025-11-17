package com.sopetit.data.entity.response.auth

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshResponseDto(
    @SerializedName("accessToken")
    val accessToken: String = "",
)
