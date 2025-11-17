package com.sopetit.domain.usecase.memberchallenge

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.repository.MemberChallengeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AchieveMemberChallengeUseCase
    @Inject
    constructor(
        private val memberChallengeRepository: MemberChallengeRepository,
    ) : UseCase<Unit, Result<Unit>>() {
        override suspend fun invoke(request: Unit): Flow<Result<Unit>> =
            memberChallengeRepository.achieveMemberChallenge()
    }
