package com.sopetit.domain.usecase.achieve

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.entity.response.achieve.AchieveRoutineModel
import com.sopetit.domain.repository.AchieveRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAchieveRoutineUseCase @Inject constructor(
    private val achieveRepository: AchieveRepository,
) : UseCase<Int, Result<AchieveRoutineModel>>() {

    override suspend fun invoke(request: Int): Flow<Result<AchieveRoutineModel>> =
        achieveRepository.getAchieveRoutine(request)
}