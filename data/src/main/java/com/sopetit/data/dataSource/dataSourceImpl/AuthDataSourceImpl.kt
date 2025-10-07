package com.sopetit.data.dataSource.dataSourceImpl

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.dataSource.AuthDataSource
import com.sopetit.data.entity.request.LogInRequestDto
import com.sopetit.data.entity.response.auth.LogInResponseDto
import com.sopetit.data.service.AuthService
import retrofit2.Response
import javax.inject.Inject

data class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthDataSource {

    override suspend fun postLogIn(request: LogInRequestDto): Response<BaseResponse<LogInResponseDto>> =
        authService.login(request)

    override suspend fun deleteUser(): Response<BaseResponse<Unit>> =
        authService.deleteUser()

    override suspend fun postLogOut(): Response<BaseResponse<Unit>> =
        authService.logout()
}