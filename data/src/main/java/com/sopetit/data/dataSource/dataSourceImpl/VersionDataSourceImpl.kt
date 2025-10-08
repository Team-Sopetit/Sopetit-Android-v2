package com.sopetit.data.dataSource.dataSourceImpl

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.dataSource.VersionDataSource
import com.sopetit.data.entity.response.version.VersionResponseDto
import com.sopetit.data.service.VersionService
import retrofit2.Response
import javax.inject.Inject

class VersionDataSourceImpl @Inject constructor(
    private val versionService: VersionService
): VersionDataSource {

    override suspend fun getVersion(): Response<BaseResponse<VersionResponseDto>> =
        versionService.getVersion()
}