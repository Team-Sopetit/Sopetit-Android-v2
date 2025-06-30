package com.sopetit.domain.repository

import com.sopetit.domain.entity.response.achieve.AchieveModel
import kotlinx.coroutines.flow.Flow

interface AchieveRepository {
    suspend fun getAchieve(): Flow<Result<AchieveModel>>
}