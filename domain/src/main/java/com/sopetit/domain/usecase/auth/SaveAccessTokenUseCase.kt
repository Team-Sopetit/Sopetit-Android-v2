package com.sopetit.domain.usecase.auth

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.repository.RefreshTokenRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveAccessTokenUseCase @Inject constructor(
    private val refreshTokenRepository: RefreshTokenRepository,
) : UseCase<String, Result<Unit>>() {

    override suspend fun invoke(request: String): Flow<Result<Unit>> {
        return refreshTokenRepository.saveAccessToken(request)
    }
}