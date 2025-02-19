package com.sopetit.data.dataSource.dataSourceImpl

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.dataSource.RoutineDataSource
import com.sopetit.data.entity.request.routine.DailyRoutineListRequestDto
import com.sopetit.data.entity.response.routine.DailyRoutineListResponseDto
import com.sopetit.data.service.RoutineService
import retrofit2.Response
import javax.inject.Inject

data class RoutineDataSourceImpl @Inject constructor(
    private val routineService: RoutineService
) : RoutineDataSource {

    override suspend fun getDailyRoutine(request: DailyRoutineListRequestDto): Response<BaseResponse<DailyRoutineListResponseDto>> =
        routineService.routineList(request)
}