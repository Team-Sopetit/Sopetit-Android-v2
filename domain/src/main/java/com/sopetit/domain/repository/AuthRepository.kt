package com.sopetit.domain.repository

import com.sopetit.domain.entity.request.LogInRequestModel
import com.sopetit.domain.entity.response.auth.LogInResponseModel
import com.sopetit.domain.entity.response.auth.TokenStoreModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun postLogIn(request: LogInRequestModel): Flow<Result<LogInResponseModel>>
    suspend fun saveToken(request: TokenStoreModel): Flow<Result<Unit>>
    suspend fun getToken(): Flow<Result<TokenStoreModel>>
    suspend fun deleteUser(): Flow<Result<Unit>>
    suspend fun postLogOut(): Flow<Result<Unit>>
    suspend fun saveMemberDollExist(request: Boolean): Flow<Result<Unit>>
    suspend fun getMemberDollExist(): Flow<Result<Boolean>>
}