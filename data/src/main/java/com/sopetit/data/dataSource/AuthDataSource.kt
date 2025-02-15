package com.sopetit.data.dataSource

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.request.LogInRequestDto
import com.sopetit.data.entity.response.LogInResponseDto

interface AuthDataSource {
    suspend fun postLogIn(request: LogInRequestDto): BaseResponse<LogInResponseDto>
}