package com.sopetit.data.service

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.base.EndPoints
import com.sopetit.data.entity.response.routine.DailyRoutineListResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RoutineService {

    @GET(EndPoints.Routine.ROUTINE)
    suspend fun routineList(
        @Query("themeIds") themeIds: List<Int>
    ): Response<BaseResponse<DailyRoutineListResponseDto>>
}