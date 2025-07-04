package com.sopetit.domain.repository

import com.sopetit.domain.entity.response.achieve.AchieveModel
import com.sopetit.domain.entity.response.achieve.AchieveRoutineModel
import kotlinx.coroutines.flow.Flow

interface AchieveRepository {
    suspend fun getAchieve(): Flow<Result<AchieveModel>>
    suspend fun getAchieveRoutine(themeId: Int): Flow<Result<AchieveRoutineModel>>
}