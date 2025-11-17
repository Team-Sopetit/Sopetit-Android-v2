package com.sopetit.data.mapper.member

import com.sopetit.data.base.BaseMapper
import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.response.member.GetMemberResponseDto
import com.sopetit.domain.entity.response.member.GetMemberModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object GetMemberMapper : BaseMapper() {
    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<GetMemberResponseDto>>): Flow<Result<GetMemberModel>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.let { data ->
                    GetMemberModel(
                        name = data.name,
                        dollType = data.dollType,
                        dailyCottonCount = data.dailyCottonCount,
                        happinessCottonCount = data.happinessCottonCount,
                        conversations = data.conversations,
                        frameImageUrl = data.frameImageUrl,
                    )
                } ?: GetMemberModel()
            },
        )
    }
}
