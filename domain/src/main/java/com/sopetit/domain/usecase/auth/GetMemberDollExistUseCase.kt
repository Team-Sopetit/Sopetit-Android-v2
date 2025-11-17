package com.sopetit.domain.usecase.auth

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMemberDollExistUseCase
    @Inject
    constructor(
        private val authRepository: AuthRepository,
    ) : UseCase<Unit, Result<Boolean>>() {
        override suspend fun invoke(request: Unit): Flow<Result<Boolean>> {
            return authRepository.getMemberDollExist()
        }
    }
