package com.sopetit.data.dataSource

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.response.version.VersionResponseDto
import retrofit2.Response

interface VersionDataSource {
    suspend fun getVersion(): Response<BaseResponse<VersionResponseDto>>
}
