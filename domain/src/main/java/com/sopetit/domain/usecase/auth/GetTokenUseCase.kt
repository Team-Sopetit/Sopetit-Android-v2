package com.sopetit.domain.usecase.auth

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.entity.response.auth.TokenStoreModel
import com.sopetit.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTokenUseCase
    @Inject
    constructor(
        private val authRepository: AuthRepository,
    ) : UseCase<Unit, Result<TokenStoreModel>>() {
        override suspend fun invoke(request: Unit): Flow<Result<TokenStoreModel>> {
            return authRepository.getToken()
        }
    }
