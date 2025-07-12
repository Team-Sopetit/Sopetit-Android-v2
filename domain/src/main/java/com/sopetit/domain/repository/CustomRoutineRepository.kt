package com.sopetit.domain.repository

import com.sopetit.domain.entity.request.customroutine.CreateCustomRoutineRequestModel
import com.sopetit.domain.entity.response.customroutine.CreateCustomRoutineModel
import kotlinx.coroutines.flow.Flow

interface CustomRoutineRepository {
    suspend fun postCreateCustomRoutine(request: CreateCustomRoutineRequestModel): Flow<Result<CreateCustomRoutineModel>>
}