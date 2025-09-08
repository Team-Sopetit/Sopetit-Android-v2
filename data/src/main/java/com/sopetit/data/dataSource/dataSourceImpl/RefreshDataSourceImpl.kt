package com.sopetit.data.dataSource.dataSourceImpl

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.dataSource.RefreshDataSource
import com.sopetit.data.entity.response.auth.RefreshResponseDto
import com.sopetit.data.service.RefreshTokenService
import retrofit2.Response
import javax.inject.Inject

class RefreshDataSourceImpl @Inject constructor(
    private val refreshTokenService: RefreshTokenService
): RefreshDataSource {

    override suspend fun postRefreshToken(): Response<BaseResponse<RefreshResponseDto>> =
        refreshTokenService.refreshToken()
}