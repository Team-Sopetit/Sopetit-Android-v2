package com.sopetit.data.mapper

import com.sopetit.data.entity.request.LogInRequestDto
import com.sopetit.data.entity.response.LogInResponseDto
import com.sopetit.domain.entity.request.LogInRequestModel
import com.sopetit.domain.entity.response.LogInResponseModel

fun LogInRequestModel.toDto() = LogInRequestDto(
    socialType = socialType
)

fun responseToModel(response: LogInResponseDto?): LogInResponseModel {
    return response?.let {
        LogInResponseModel(
            accessToken = it.accessToken,
            refreshToken = it.refreshToken,
            isMemberDollExist = it.isMemberDollExist
        )
    } ?: LogInResponseModel()
}
