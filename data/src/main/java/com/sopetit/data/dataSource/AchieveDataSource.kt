package com.sopetit.data.dataSource

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.response.achieve.GetAchieveResponseDto
import retrofit2.Response

interface AchieveDataSource {
    suspend fun getAchievement(): Response<BaseResponse<GetAchieveResponseDto>>
}