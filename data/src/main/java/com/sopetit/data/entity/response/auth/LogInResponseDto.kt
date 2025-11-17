package com.sopetit.data.entity.response.auth

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class LogInResponseDto(
    @SerializedName("accessToken")
    val accessToken: String = "",
    @SerializedName("refreshToken")
    val refreshToken: String = "",
    @SerializedName("isMemberDollExist")
    val isMemberDollExist: Boolean = false,
)
