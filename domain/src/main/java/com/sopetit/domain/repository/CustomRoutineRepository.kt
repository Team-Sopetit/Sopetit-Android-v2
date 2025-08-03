package com.sopetit.domain.repository

import com.sopetit.domain.entity.request.customroutine.CustomRoutineRequestModel
import com.sopetit.domain.entity.request.customroutine.ModifyCustomRoutineRequestModel
import com.sopetit.domain.entity.response.customroutine.CustomRoutineModel
import kotlinx.coroutines.flow.Flow

interface CustomRoutineRepository {
    suspend fun postCreateCustomRoutine(request: CustomRoutineRequestModel): Flow<Result<CustomRoutineModel>>
    suspend fun modifyCreateCustomRoutine(request: ModifyCustomRoutineRequestModel): Flow<Result<CustomRoutineModel>>
    suspend fun deleteCustomRoutine(request: Int): Flow<Result<Unit>>
}