package com.sopetit.domain.entity.response.auth

data class TokenStoreModel (
    val accessToken: String = "",
    val refreshToken: String = "",
    val isMemberDollExist: Boolean = false
)