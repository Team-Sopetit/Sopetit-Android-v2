package com.sopetit.domain.usecase.member

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.entity.response.member.GetMemberModel
import com.sopetit.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMemberUseCase @Inject constructor(
    private val memberRepository: MemberRepository,
) : UseCase<Unit, Result<GetMemberModel>>() {

    override suspend fun invoke(request: Unit): Flow<Result<GetMemberModel>> =
        memberRepository.getMember()
}