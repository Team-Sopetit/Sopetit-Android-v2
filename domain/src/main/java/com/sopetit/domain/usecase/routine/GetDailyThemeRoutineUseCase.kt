package com.sopetit.domain.usecase.routine

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.entity.response.routine.DailyRoutineListItemModel
import com.sopetit.domain.repository.RoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDailyThemeRoutineUseCase @Inject constructor(
    private val routineRepository: RoutineRepository
): UseCase<Int, Result<List<DailyRoutineListItemModel>>>() {

    override suspend fun invoke(request: Int): Flow<Result<List<DailyRoutineListItemModel>>> =
        routineRepository.getDailyThemeRoutineList(request)
}