package com.sopetit.domain.usecase.achieve

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.entity.response.achieve.AchieveModel
import com.sopetit.domain.repository.AchieveRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAchievementUseCase @Inject constructor(
    private val achieveRepository: AchieveRepository,
) : UseCase<Unit, Result<AchieveModel>>() {

    override suspend fun invoke(request: Unit): Flow<Result<AchieveModel>> =
        achieveRepository.getAchieve()
}