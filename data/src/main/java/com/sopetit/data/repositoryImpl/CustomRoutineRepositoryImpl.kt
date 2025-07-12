package com.sopetit.data.repositoryImpl

import com.sopetit.data.dataSource.CustomRoutineDataSource
import com.sopetit.data.mapper.customroutine.CreateCustomRoutineMapper
import com.sopetit.data.mapper.customroutine.CreateCustomRoutineMapper.toDto
import com.sopetit.domain.entity.request.customroutine.CreateCustomRoutineRequestModel
import com.sopetit.domain.entity.response.customroutine.CreateCustomRoutineModel
import com.sopetit.domain.repository.CustomRoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CustomRoutineRepositoryImpl @Inject constructor(
    private val customRoutineDataSource: CustomRoutineDataSource,
) : CustomRoutineRepository {

    override suspend fun postCreateCustomRoutine(request: CreateCustomRoutineRequestModel): Flow<Result<CreateCustomRoutineModel>> =
        CreateCustomRoutineMapper.responseToModel(apiCall = {
            customRoutineDataSource.postCreateCustomRoutine(
                request.toDto()
            )
        })
}