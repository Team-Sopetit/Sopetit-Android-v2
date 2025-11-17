package com.sopetit.data.service

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.base.EndPoints
import com.sopetit.data.entity.response.auth.RefreshResponseDto
import retrofit2.Response
import retrofit2.http.POST

interface RefreshTokenService {
    @POST(EndPoints.Auth.REISSUE)
    suspend fun refreshToken(): Response<BaseResponse<RefreshResponseDto>>
}
