package com.sopetit.data.service

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.base.EndPoints
import com.sopetit.data.entity.response.routine.DailyRoutineListResponseDto
import com.sopetit.data.entity.response.routine.DailyThemeRoutineResponseDto
import com.sopetit.data.entity.response.routine.ChallengeResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RoutineService {
    @GET(EndPoints.Routine.ROUTINE)
    suspend fun routineList(
        @Query("themeIds") themeIds: List<Int>,
    ): Response<BaseResponse<DailyRoutineListResponseDto>>

    @GET(EndPoints.Routine.DAILYTYHEMEROUTINE)
    suspend fun dailyThemeRoutine(
        @Path("themeId") themeId: Int,
    ): Response<BaseResponse<DailyThemeRoutineResponseDto>>

    @GET(EndPoints.Routine.CHALLENGE)
    suspend fun challenge(
        @Query("themeId") themeId: Int,
    ): Response<BaseResponse<ChallengeResponseDto>>
}
