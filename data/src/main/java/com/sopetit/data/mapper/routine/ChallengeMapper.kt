package com.sopetit.data.mapper.routine

import com.sopetit.data.base.BaseMapper
import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.response.routine.ChallengeResponseDto
import com.sopetit.domain.entity.response.routine.ChallengeItemModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object ChallengeMapper : BaseMapper() {
    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<ChallengeResponseDto>>): Flow<Result<List<ChallengeItemModel>>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.let { data ->
                    data.challenges.map { challenge ->
                        ChallengeItemModel(
                            challengeId = challenge.challengeId,
                            content = challenge.content,
                            description = challenge.description,
                            requiredTime = challenge.requiredTime,
                            place = challenge.place,
                            hasRoutine = challenge.hasRoutine,
                        )
                    }
                } ?: listOf(ChallengeItemModel())
            },
        )
    }
}
