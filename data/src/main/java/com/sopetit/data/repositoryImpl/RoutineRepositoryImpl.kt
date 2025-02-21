package com.sopetit.data.repositoryImpl

import com.sopetit.data.dataSource.RoutineDataSource
import com.sopetit.data.mapper.routine.DailyRoutineMapper
import com.sopetit.data.mapper.routine.DailyRoutineMapper.toDto
import com.sopetit.domain.entity.request.routine.DailyRoutineListRequestModel
import com.sopetit.domain.entity.response.routine.DailyRoutineListThemeTotalModel
import com.sopetit.domain.repository.RoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoutineRepositoryImpl @Inject constructor(
    private val routineDataSource: RoutineDataSource
) : RoutineRepository {

    override suspend fun dailyRoutineList(request: DailyRoutineListRequestModel): Flow<Result<DailyRoutineListThemeTotalModel>> =
        DailyRoutineMapper.responseToModel(apiCall = { routineDataSource.getDailyRoutine(request.toDto()) })
}