package com.sopetit.data.repositoryImpl

import com.sopetit.data.dataSource.AuthDataSource
import com.sopetit.data.mapper.responseToModel
import com.sopetit.data.mapper.toDto
import com.sopetit.domain.entity.request.LogInRequestModel
import com.sopetit.domain.entity.response.LogInResponseModel
import com.sopetit.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun postLogIn(request: LogInRequestModel): Flow<Result<LogInResponseModel>> =
        responseToModel(apiCall = { authDataSource.postLogIn(request.toDto()) } )
}