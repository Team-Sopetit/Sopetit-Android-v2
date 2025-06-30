package com.sopetit.data.repositoryImpl

import com.sopetit.data.dataSource.AchieveDataSource
import com.sopetit.data.mapper.achieve.GetAchieveMapper
import com.sopetit.domain.entity.response.achieve.AchieveModel
import com.sopetit.domain.repository.AchieveRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AchieveRepositoryImpl @Inject constructor(
    private val achieveDataSource: AchieveDataSource,
) : AchieveRepository {

    override suspend fun getAchieve(): Flow<Result<AchieveModel>> =
        GetAchieveMapper.responseToModel(apiCall = { achieveDataSource.getAchievement() })
}