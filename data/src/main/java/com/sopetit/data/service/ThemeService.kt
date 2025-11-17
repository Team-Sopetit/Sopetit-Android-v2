package com.sopetit.data.service

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.base.EndPoints
import com.sopetit.data.entity.response.theme.ThemeListResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface ThemeService {
    @GET(EndPoints.Theme.THEME)
    suspend fun themeList(): Response<BaseResponse<ThemeListResponseDto>>
}
