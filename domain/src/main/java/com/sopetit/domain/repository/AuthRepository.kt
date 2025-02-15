package com.sopetit.domain.repository

import com.sopetit.domain.entity.request.LogInRequestModel
import com.sopetit.domain.entity.response.LogInResponseModel
import com.sopetit.domain.entity.response.TokenStoreModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun postLogIn(request: LogInRequestModel): Flow<Result<LogInResponseModel>>
    suspend fun saveToken(request: TokenStoreModel): Flow<Result<Unit>>
}