package com.sopetit.domain.usecase.member

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostFcmTokenUseCase @Inject constructor(
    private val memberRepository: MemberRepository
): UseCase<Unit, Result<Unit>>() {

    override suspend fun invoke(request: Unit): Flow<Result<Unit>> =
        memberRepository.postFcmToken()
}