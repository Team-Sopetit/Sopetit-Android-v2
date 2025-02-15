package com.sopetit.domain.repository

import com.sopetit.domain.entity.request.LogInRequestModel
import com.sopetit.domain.entity.response.LogInResponseModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun postLogIn(request: LogInRequestModel): Flow<Result<LogInResponseModel>>
}