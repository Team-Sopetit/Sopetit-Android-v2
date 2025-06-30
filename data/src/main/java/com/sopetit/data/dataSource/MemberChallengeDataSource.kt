package com.sopetit.data.dataSource

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.request.memberchallenge.AddMemberChallengeRequestDto
import com.sopetit.data.entity.response.memberchallenge.AddMemberChallengeResponseDto
import com.sopetit.data.entity.response.memberchallenge.GetMemberChallengeResponseDto
import retrofit2.Response

interface MemberChallengeDataSource {
    suspend fun getMemberChallenge(): Response<BaseResponse<GetMemberChallengeResponseDto>>

    suspend fun deleteMemberChallenge(): Response<BaseResponse<Unit>>

    suspend fun achieveMemberChallenge(): Response<BaseResponse<Unit>>

    suspend fun addMemberChallenge(request: AddMemberChallengeRequestDto): Response<BaseResponse<AddMemberChallengeResponseDto>>

    suspend fun deleteChallengeHistory(request: Int): Response<BaseResponse<Unit>>
}