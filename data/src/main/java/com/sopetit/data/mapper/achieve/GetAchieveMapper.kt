package com.sopetit.data.mapper.achieve

import com.sopetit.data.base.BaseMapper
import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.response.achieve.GetAchieveResponseDto
import com.sopetit.domain.entity.response.achieve.AchieveModel
import com.sopetit.domain.entity.response.achieve.AchieveThemeItemModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object GetAchieveMapper: BaseMapper() {

    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<GetAchieveResponseDto>>): Flow<Result<AchieveModel>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.let { data ->
                    AchieveModel(
                        achievedCount = data.achievedCount,
                        themes = data.themes.map { theme ->
                            AchieveThemeItemModel(
                                id = theme.id,
                                name = theme.name,
                                achievedCount = theme.achievedCount
                            )
                        }
                    )
                } ?: AchieveModel()
            }
        )
    }
}