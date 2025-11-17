package com.sopetit.domain.usecase.auth

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.entity.response.auth.TokenStoreModel
import com.sopetit.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveTokenUseCase
    @Inject
    constructor(
        private val authRepository: AuthRepository,
    ) : UseCase<TokenStoreModel, Result<Unit>>() {
        override suspend fun invoke(request: TokenStoreModel): Flow<Result<Unit>> {
            return authRepository.saveToken(request)
        }
    }
