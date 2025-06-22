package com.sopetit.domain.usecase.routine

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.entity.response.routine.ChallengeItemModel
import com.sopetit.domain.repository.RoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChallengeUseCase @Inject constructor(
    private val routineRepository: RoutineRepository,
) : UseCase<Int, Result<List<ChallengeItemModel>>>() {

    override suspend fun invoke(request: Int): Flow<Result<List<ChallengeItemModel>>> =
        routineRepository.getChallenge(request)
}