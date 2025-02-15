package com.sopetit.data.entity.response

import com.google.gson.annotations.SerializedName

//@Serializable
data class LogInResponseDto(
    @SerializedName("accessToken")
    val accessToken: String = "",
    @SerializedName("refreshToken")
    val refreshToken: String = "",
    @SerializedName("isMemberDollExist")
    val isMemberDollExist: Boolean = false
)