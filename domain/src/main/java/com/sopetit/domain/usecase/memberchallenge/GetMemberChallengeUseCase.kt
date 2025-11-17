package com.sopetit.domain.usecase.memberchallenge

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.entity.response.memberchallenge.MemberChallengeModel
import com.sopetit.domain.repository.MemberChallengeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMemberChallengeUseCase
    @Inject
    constructor(
        private val memberChallengeRepository: MemberChallengeRepository,
    ) : UseCase<Unit, Result<MemberChallengeModel>>() {
        override suspend fun invoke(request: Unit): Flow<Result<MemberChallengeModel>> =
            memberChallengeRepository.getMemberChallenge()
    }
