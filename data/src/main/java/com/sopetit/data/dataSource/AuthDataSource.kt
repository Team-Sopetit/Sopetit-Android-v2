package com.sopetit.data.dataSource

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.request.LogInRequestDto
import com.sopetit.data.entity.response.LogInResponseDto
import retrofit2.Response

interface AuthDataSource {
    suspend fun postLogIn(request: LogInRequestDto): Response<BaseResponse<LogInResponseDto>>
}