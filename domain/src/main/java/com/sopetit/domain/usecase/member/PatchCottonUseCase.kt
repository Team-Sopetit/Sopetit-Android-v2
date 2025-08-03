package com.sopetit.domain.usecase.member

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PatchCottonUseCase @Inject constructor(
    private val memberRepository: MemberRepository
): UseCase<String, Result<Int>>() {

    override suspend fun invoke(request: String): Flow<Result<Int>> =
        memberRepository.patchCotton(request)
}