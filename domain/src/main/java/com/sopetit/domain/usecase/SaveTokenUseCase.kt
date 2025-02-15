package com.sopetit.domain.usecase

import com.sopetit.domain.UseCase
import com.sopetit.domain.entity.response.TokenStoreModel
import com.sopetit.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
): UseCase<TokenStoreModel, Result<Unit>>() {

    override suspend fun invoke(request: TokenStoreModel): Flow<Result<Unit>> {
        return authRepository.saveToken(request)
    }
}