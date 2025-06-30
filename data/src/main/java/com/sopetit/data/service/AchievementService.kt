package com.sopetit.data.service

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.base.EndPoints
import com.sopetit.data.entity.response.achieve.GetAchieveResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface AchievementService {

    @GET(EndPoints.Achievement.ACHIEVE)
    suspend fun getAchievement(): Response<BaseResponse<GetAchieveResponseDto>>
}