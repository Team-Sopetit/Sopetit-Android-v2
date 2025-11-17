package com.sopetit.domain.usecase.auth

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.entity.response.auth.AccessToken
import com.sopetit.domain.repository.RefreshTokenRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RefreshTokenUseCase
    @Inject
    constructor(
        private val refreshTokenRepository: RefreshTokenRepository,
    ) : UseCase<Unit, Result<AccessToken>>() {
        override suspend fun invoke(request: Unit): Flow<Result<AccessToken>> {
            return refreshTokenRepository.refreshToken()
        }
    }
