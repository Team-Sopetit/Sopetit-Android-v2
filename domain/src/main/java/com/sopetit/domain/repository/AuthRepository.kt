package com.sopetit.domain.repository

import com.sopetit.domain.entity.request.LogInRequestModel
import com.sopetit.domain.entity.response.LogInResponseModel

interface AuthRepository {
    suspend fun postLogIn(request: LogInRequestModel): Result<LogInResponseModel>
}