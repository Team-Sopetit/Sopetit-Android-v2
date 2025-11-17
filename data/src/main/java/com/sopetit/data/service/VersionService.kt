package com.sopetit.data.service

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.base.EndPoints
import com.sopetit.data.entity.response.version.VersionResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface VersionService {
    @GET(EndPoints.Version.VERSION)
    suspend fun getVersion(): Response<BaseResponse<VersionResponseDto>>
}
