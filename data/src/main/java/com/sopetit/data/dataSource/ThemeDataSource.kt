package com.sopetit.data.dataSource

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.response.theme.ThemeListResponseDto
import retrofit2.Response

interface ThemeDataSource {
    suspend fun themeList(): Response<BaseResponse<ThemeListResponseDto>>
}