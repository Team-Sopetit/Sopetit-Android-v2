package com.sopetit.data.mapper.memberchallenge

import com.sopetit.data.base.BaseMapper
import com.sopetit.data.base.BaseResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object AchieveMemberChallengeMapper : BaseMapper() {
    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<Unit>>): Flow<Result<Unit>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = {},
        )
    }
}
