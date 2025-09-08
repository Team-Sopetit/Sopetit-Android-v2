package com.sopetit.domain.repository

import com.sopetit.domain.entity.response.auth.AccessToken
import kotlinx.coroutines.flow.Flow

interface RefreshTokenRepository {
    suspend fun refreshToken(): Flow<Result<AccessToken>>
    suspend fun saveToken(request: String): Flow<Result<Unit>>
}