package com.sopetit.data.repositoryImpl

import com.sopetit.data.dataSource.AuthDataSource
import com.sopetit.data.dataStore.LocalDataStore
import com.sopetit.data.mapper.DefaultUnitMapper
import com.sopetit.data.mapper.auth.LogInMapper
import com.sopetit.data.mapper.auth.LogInMapper.toDto
import com.sopetit.domain.entity.request.LogInRequestModel
import com.sopetit.domain.entity.response.auth.LogInResponseModel
import com.sopetit.domain.entity.response.auth.TokenStoreModel
import com.sopetit.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val localDataStore: LocalDataStore,
) : AuthRepository {
    override suspend fun postLogIn(request: LogInRequestModel): Flow<Result<LogInResponseModel>> =
        LogInMapper.responseToModel(apiCall = { authDataSource.postLogIn(request.toDto()) })

    override suspend fun saveToken(request: TokenStoreModel): Flow<Result<Unit>> = flow {
        localDataStore.saveAccessToken(request.accessToken)
        localDataStore.saveRefreshToken(request.refreshToken)
        localDataStore.saveIsMemberDollExist(request.isMemberDollExist)
    }

    override suspend fun getToken(): Flow<Result<TokenStoreModel>> = flow {
        emitAll(
            combine(
                localDataStore.accessToken,
                localDataStore.refreshToken,
                localDataStore.isMemberDollExist
            ) { accessToken, refreshToken, isDollExist ->
                TokenStoreModel(
                    accessToken = accessToken.orEmpty(),
                    refreshToken = refreshToken.orEmpty(),
                    isMemberDollExist = isDollExist ?: false
                )
            }
                .distinctUntilChanged()
                .map { Result.success(it) }
        )
    }

    override suspend fun deleteUser(): Flow<Result<Unit>> =
        DefaultUnitMapper.responseToModel(apiCall = { authDataSource.deleteUser() })

    override suspend fun postLogOut(): Flow<Result<Unit>> =
        DefaultUnitMapper.responseToModel(apiCall = { authDataSource.postLogOut() })
}