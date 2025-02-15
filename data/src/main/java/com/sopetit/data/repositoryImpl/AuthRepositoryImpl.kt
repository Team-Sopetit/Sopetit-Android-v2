package com.sopetit.data.repositoryImpl

import com.sopetit.data.dataSource.AuthDataSource
import com.sopetit.data.dataStore.LocalDataStore
import com.sopetit.data.mapper.LogInMapper
import com.sopetit.data.mapper.LogInMapper.toDto
import com.sopetit.domain.entity.request.LogInRequestModel
import com.sopetit.domain.entity.response.LogInResponseModel
import com.sopetit.domain.entity.response.TokenStoreModel
import com.sopetit.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val localDataStore: LocalDataStore
) : AuthRepository {
    override suspend fun postLogIn(request: LogInRequestModel): Flow<Result<LogInResponseModel>> =
        LogInMapper.responseToModel(apiCall = { authDataSource.postLogIn(request.toDto()) } )

    override suspend fun saveToken(request: TokenStoreModel): Flow<Result<Unit>> = flow {
        localDataStore.saveAccessToken(request.accessToken)
        localDataStore.saveRefreshToken(request.refreshToken)
        localDataStore.saveIsMemberDollExist(request.isMemberDollExist)
    }
}