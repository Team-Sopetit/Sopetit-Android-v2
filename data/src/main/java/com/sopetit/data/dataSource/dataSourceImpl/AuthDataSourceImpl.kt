package com.sopetit.data.dataSource.dataSourceImpl

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.dataSource.AuthDataSource
import com.sopetit.data.entity.request.LogInRequestDto
import com.sopetit.data.entity.response.LogInResponseDto
import com.sopetit.data.service.AuthService
import javax.inject.Inject

data class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthDataSource {

    override suspend fun postLogIn(request: LogInRequestDto): BaseResponse<LogInResponseDto> =
        authService.login(request)

}