package com.sopetit.data.mapper.theme

import com.sopetit.data.base.BaseMapper
import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.response.theme.ThemeListResponseDto
import com.sopetit.domain.entity.response.theme.ThemeListItemModel
import com.sopetit.domain.entity.response.theme.ThemeListModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object ThemeListMapper : BaseMapper() {
    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<ThemeListResponseDto>>): Flow<Result<ThemeListModel>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.let { data ->
                    ThemeListModel(
                        themes =
                            data.themes.map { item ->
                                ThemeListItemModel(
                                    themeId = item.themeId,
                                    title = item.title,
                                    subTitle = item.subTitle,
                                    description = item.description,
                                )
                            },
                    )
                } ?: ThemeListModel()
            },
        )
    }
}
