package com.sopetit.domain.entity.response

data class TokenStoreModel (
    val accessToken: String = "",
    val refreshToken: String = ""
)