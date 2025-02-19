package com.sopetit.domain.usecase.routine

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.entity.request.routine.DailyRoutineListRequestModel
import com.sopetit.domain.entity.response.routine.DailyRoutineListThemeTotalModel
import com.sopetit.domain.repository.RoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDailyRoutineUseCase @Inject constructor(
    private val routineRepository: RoutineRepository
) : UseCase<DailyRoutineListRequestModel, Result<DailyRoutineListThemeTotalModel>>() {

    override suspend fun invoke(request: DailyRoutineListRequestModel): Flow<Result<DailyRoutineListThemeTotalModel>> =
        routineRepository.dailyRoutineList(request)
}