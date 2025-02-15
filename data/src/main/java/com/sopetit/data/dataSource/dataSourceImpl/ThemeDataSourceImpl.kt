package com.sopetit.data.dataSource.dataSourceImpl

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.dataSource.ThemeDataSource
import com.sopetit.data.entity.response.theme.ThemeListResponseDto
import com.sopetit.data.service.ThemeService
import retrofit2.Response
import javax.inject.Inject

class ThemeDataSourceImpl @Inject constructor(
    private val themeService: ThemeService
) : ThemeDataSource {

    override suspend fun themeList(): Response<BaseResponse<ThemeListResponseDto>> =
        themeService.themeList()

}