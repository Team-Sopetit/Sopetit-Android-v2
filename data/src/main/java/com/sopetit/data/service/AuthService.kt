package com.sopetit.data.service

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.base.EndPoints
import com.sopetit.data.entity.request.LogInRequestDto
import com.sopetit.data.entity.response.auth.LogInResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST(EndPoints.Auth.LOGIN)
    suspend fun login(
        @Body body: LogInRequestDto
    ): Response<BaseResponse<LogInResponseDto>>
}