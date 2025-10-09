package com.sopetit.domain.usecase.auth

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveMemberDollExistUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) : UseCase<Boolean, Result<Unit>>() {

    override suspend fun invoke(request: Boolean): Flow<Result<Unit>> {
        return authRepository.saveMemberDollExist(request)
    }
}