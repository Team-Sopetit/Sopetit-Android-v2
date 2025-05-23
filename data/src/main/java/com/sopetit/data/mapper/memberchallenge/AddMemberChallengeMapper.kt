package com.sopetit.data.mapper.memberchallenge

import com.sopetit.data.base.BaseMapper
import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.request.memberchallenge.AddMemberChallengeRequestDto
import com.sopetit.data.entity.response.memberchallenge.AddMemberChallengeResponseDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object AddMemberChallengeMapper: BaseMapper() {

    fun intToDto(request: Int) = AddMemberChallengeRequestDto(request)

    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<AddMemberChallengeResponseDto>>): Flow<Result<Int>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.memberChallengeId ?: 0
            }
        )
    }
}