package com.sopetit.domain.usecase.memberroutine

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.entity.response.memberroutine.MemberDailyRoutineTotalModel
import com.sopetit.domain.repository.MemberRoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMemberRoutineUseCase @Inject constructor(
    private val memberRoutineRepository: MemberRoutineRepository,
) : UseCase<Unit, Result<MemberDailyRoutineTotalModel>>() {

    override suspend fun invoke(request: Unit): Flow<Result<MemberDailyRoutineTotalModel>> =
        memberRoutineRepository.getMemberDailyRoutine()
}