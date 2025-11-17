package com.sopetit.data.mapper.memberchallenge

import com.sopetit.data.base.BaseMapper
import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.response.memberchallenge.GetMemberChallengeResponseDto
import com.sopetit.domain.entity.response.memberchallenge.MemberChallengeModel
import com.sopetit.domain.entity.response.theme.ChallengeThemeItemModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object GetMemberChallengeMapper : BaseMapper() {
    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<GetMemberChallengeResponseDto>>): Flow<Result<MemberChallengeModel>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.let { data ->
                    MemberChallengeModel(
                        memberChallengeId = data.memberChallengeId,
                        theme = ChallengeThemeItemModel(data.theme.themeId, data.theme.themeName),
                        content = data.content,
                        description = data.description,
                        place = data.place,
                        timeTaken = data.timeTaken,
                    )
                } ?: MemberChallengeModel()
            },
        )
    }
}
