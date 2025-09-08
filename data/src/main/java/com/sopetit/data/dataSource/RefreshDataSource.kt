package com.sopetit.data.dataSource

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.response.auth.RefreshResponseDto
import retrofit2.Response

interface RefreshDataSource {
    suspend fun postRefreshToken(): Response<BaseResponse<RefreshResponseDto>>
}