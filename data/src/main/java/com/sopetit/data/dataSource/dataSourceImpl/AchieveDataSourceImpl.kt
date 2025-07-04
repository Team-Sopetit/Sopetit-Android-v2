package com.sopetit.data.dataSource.dataSourceImpl

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.dataSource.AchieveDataSource
import com.sopetit.data.entity.response.achieve.GetAchieveResponseDto
import com.sopetit.data.entity.response.achieve.GetAchieveRoutineResponseDto
import com.sopetit.data.service.AchievementService
import retrofit2.Response
import javax.inject.Inject

class AchieveDataSourceImpl @Inject constructor(
    private val achievementService: AchievementService
): AchieveDataSource {

    override suspend fun getAchievement(): Response<BaseResponse<GetAchieveResponseDto>> =
        achievementService.getAchievement()

    override suspend fun getAchieveRoutine(themeId: Int): Response<BaseResponse<GetAchieveRoutineResponseDto>> =
        achievementService.getAchieveRoutine(themeId)
}