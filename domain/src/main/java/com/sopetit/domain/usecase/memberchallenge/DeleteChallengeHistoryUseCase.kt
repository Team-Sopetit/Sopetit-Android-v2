package com.sopetit.domain.usecase.memberchallenge

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.repository.MemberChallengeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteChallengeHistoryUseCase
    @Inject
    constructor(
        private val memberChallengeRepository: MemberChallengeRepository,
    ) : UseCase<Int, Result<Unit>>() {
        override suspend fun invoke(request: Int): Flow<Result<Unit>> =
            memberChallengeRepository.deleteChallengeHistory(request)
    }
