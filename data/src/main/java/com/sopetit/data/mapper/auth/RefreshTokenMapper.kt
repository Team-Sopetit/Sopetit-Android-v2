package com.sopetit.data.mapper.auth

import com.sopetit.data.base.BaseMapper
import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.response.auth.RefreshResponseDto
import com.sopetit.domain.entity.response.auth.AccessToken
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object RefreshTokenMapper : BaseMapper() {
    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<RefreshResponseDto>>): Flow<Result<AccessToken>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.let { data ->
                    AccessToken(data.accessToken)
                } ?: AccessToken()
            },
        )
    }
}
