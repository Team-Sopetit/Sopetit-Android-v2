package com.sopetit.domain.usecase.memberchallenge

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.repository.MemberChallengeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddMemberChallengeUseCase @Inject constructor(
    private val memberChallengeRepository: MemberChallengeRepository
): UseCase<Int, Result<Int>>() {

    override suspend fun invoke(request: Int): Flow<Result<Int>> =
        memberChallengeRepository.addMemberChallenge(request)
}