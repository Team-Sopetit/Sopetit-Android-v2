package com.sopetit.domain.usecase.memberroutine

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.entity.response.memberroutine.AchieveDailyRoutineModel
import com.sopetit.domain.repository.MemberRoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AchieveDailyRoutineUseCase @Inject constructor(
    private val memberRoutineRepository: MemberRoutineRepository,
) : UseCase<Int, Result<AchieveDailyRoutineModel>>() {

    override suspend fun invoke(request: Int): Flow<Result<AchieveDailyRoutineModel>> =
        memberRoutineRepository.achieveDailyRoutine(request)
}