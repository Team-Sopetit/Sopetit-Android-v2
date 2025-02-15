package com.sopetit.data.mapper

import com.sopetit.data.base.BaseMapper
import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.request.LogInRequestDto
import com.sopetit.data.entity.response.LogInResponseDto
import com.sopetit.domain.entity.request.LogInRequestModel
import com.sopetit.domain.entity.response.LogInResponseModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object LogInMapper: BaseMapper() {
    fun LogInRequestModel.toDto() = LogInRequestDto(
        socialType = socialType
    )

    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<LogInResponseDto>>): Flow<Result<LogInResponseModel>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.let { data ->
                    LogInResponseModel(
                        accessToken = data.accessToken,
                        refreshToken = data.refreshToken,
                        isMemberDollExist = data.isMemberDollExist
                    )
                } ?: LogInResponseModel()
            }
        )
    }
}