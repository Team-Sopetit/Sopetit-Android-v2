package com.sopetit.data.mapper.member

import com.sopetit.data.base.BaseMapper
import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.response.member.CottonResponseDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object PatchCottonMapper : BaseMapper() {
    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<CottonResponseDto>>): Flow<Result<Int>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.cottonCount ?: 0
            },
        )
    }
}
