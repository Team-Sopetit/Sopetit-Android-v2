package com.sopetit.domain.usecase

import com.sopetit.domain.UseCase
import com.sopetit.domain.entity.request.LogInRequestModel
import com.sopetit.domain.entity.response.LogInResponseModel
import com.sopetit.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostLogInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<LogInRequestModel, Result<LogInResponseModel>>() {

    override suspend fun invoke(request: LogInRequestModel): Flow<Result<LogInResponseModel>> =
        authRepository.postLogIn(request)
}