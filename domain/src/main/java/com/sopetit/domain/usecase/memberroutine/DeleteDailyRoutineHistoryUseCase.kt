package com.sopetit.domain.usecase.memberroutine

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.repository.MemberRoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteDailyRoutineHistoryUseCase @Inject constructor(
    private val memberRoutineRepository: MemberRoutineRepository,
) : UseCase<Int, Result<Unit>>() {

    override suspend fun invoke(request: Int): Flow<Result<Unit>> {
        return memberRoutineRepository.deleteRoutineHistory(request)
    }
}