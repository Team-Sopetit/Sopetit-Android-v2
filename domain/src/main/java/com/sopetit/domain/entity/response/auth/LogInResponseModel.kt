package com.sopetit.domain.entity.response.auth

data class LogInResponseModel (
    val accessToken: String = "",
    val refreshToken: String = "",
    val isMemberDollExist: Boolean = false
)