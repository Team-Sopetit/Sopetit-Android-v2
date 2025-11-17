package com.sopetit.data.service

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.base.EndPoints
import com.sopetit.data.entity.response.achieve.GetAchieveResponseDto
import com.sopetit.data.entity.response.achieve.GetAchieveRoutineResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AchievementService {
    @GET(EndPoints.Achievement.ACHIEVE)
    suspend fun getAchievement(): Response<BaseResponse<GetAchieveResponseDto>>

    @GET(EndPoints.Achievement.ACHIEVEROUTINE)
    suspend fun getAchieveRoutine(
        @Path("themeId") themeId: Int,
    ): Response<BaseResponse<GetAchieveRoutineResponseDto>>
}
