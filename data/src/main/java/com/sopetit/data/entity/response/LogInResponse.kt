package com.sopetit.data.entity.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

//@Serializable
data class LogInResponse(
    @SerializedName("accessToken")
    val accessToken: String = "",
    @SerializedName("refreshToken")
    val refreshToken: String = "",
    @SerializedName("isMemberDollExist")
    val isMemberDollExist: Boolean = false
)