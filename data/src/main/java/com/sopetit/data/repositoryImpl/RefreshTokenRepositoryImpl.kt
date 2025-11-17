package com.sopetit.data.repositoryImpl

import com.sopetit.data.dataSource.RefreshDataSource
import com.sopetit.data.dataStore.LocalDataStore
import com.sopetit.data.mapper.auth.RefreshTokenMapper
import com.sopetit.domain.entity.response.auth.AccessToken
import com.sopetit.domain.repository.RefreshTokenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RefreshTokenRepositoryImpl
    @Inject
    constructor(
        private val refreshDataSource: RefreshDataSource,
        private val localDataStore: LocalDataStore,
    ) : RefreshTokenRepository {
        override suspend fun refreshToken(): Flow<Result<AccessToken>> =
            RefreshTokenMapper.responseToModel(apiCall = { refreshDataSource.postRefreshToken() })

        override suspend fun saveAccessToken(request: String): Flow<Result<Unit>> =
            flow {
                localDataStore.saveAccessToken(request)
            }
    }
