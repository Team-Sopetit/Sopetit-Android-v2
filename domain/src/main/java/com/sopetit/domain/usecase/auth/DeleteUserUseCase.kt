package com.sopetit.domain.usecase.auth

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
): UseCase<Unit, Result<Unit>>() {

    override suspend fun invoke(request: Unit): Flow<Result<Unit>> {
        return authRepository.deleteUser()
    }
}