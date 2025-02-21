package com.sopetit.domain.usecase.member

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostCreateMemberUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) : UseCase<CreateMemberModel, Result<Unit>>() {

    override suspend fun invoke(request: CreateMemberModel): Flow<Result<Unit>> {
        return memberRepository.postCreateMember(request)
    }
}