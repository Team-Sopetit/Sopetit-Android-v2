package com.sopetit.data.dataSource

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.response.routine.DailyRoutineListResponseDto
import retrofit2.Response

interface RoutineDataSource {
    suspend fun getDailyRoutine(request: List<Int>): Response<BaseResponse<DailyRoutineListResponseDto>>
}