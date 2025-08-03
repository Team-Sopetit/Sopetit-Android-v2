package com.sopetit.data.repositoryImpl

import com.sopetit.data.dataSource.CustomRoutineDataSource
import com.sopetit.data.mapper.customroutine.CustomRoutineMapper
import com.sopetit.data.mapper.customroutine.CustomRoutineMapper.toDto
import com.sopetit.domain.entity.request.customroutine.CustomRoutineRequestModel
import com.sopetit.domain.entity.request.customroutine.ModifyCustomRoutineRequestModel
import com.sopetit.domain.entity.response.customroutine.CustomRoutineModel
import com.sopetit.domain.repository.CustomRoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CustomRoutineRepositoryImpl @Inject constructor(
    private val customRoutineDataSource: CustomRoutineDataSource,
) : CustomRoutineRepository {

    override suspend fun postCreateCustomRoutine(request: CustomRoutineRequestModel): Flow<Result<CustomRoutineModel>> =
        CustomRoutineMapper.responseToModel(apiCall = {
            customRoutineDataSource.postCreateCustomRoutine(
                request.toDto()
            )
        })

    override suspend fun modifyCreateCustomRoutine(request: ModifyCustomRoutineRequestModel): Flow<Result<CustomRoutineModel>> =
       CustomRoutineMapper.responseToModel(apiCall = {
            customRoutineDataSource.putModifyCustomRoutine(request.themeId, request.body.toDto())
        })
}