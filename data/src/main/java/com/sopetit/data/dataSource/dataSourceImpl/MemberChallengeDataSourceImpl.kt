package com.sopetit.data.dataSource.dataSourceImpl

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.dataSource.MemberChallengeDataSource
import com.sopetit.data.entity.request.memberchallenge.AddMemberChallengeRequestDto
import com.sopetit.data.entity.response.memberchallenge.AddMemberChallengeResponseDto
import com.sopetit.data.entity.response.memberchallenge.GetMemberChallengeResponseDto
import com.sopetit.data.service.MemberChallengeService
import retrofit2.Response
import javax.inject.Inject

class MemberChallengeDataSourceImpl @Inject constructor(
    private val memberChallengeService: MemberChallengeService,
) : MemberChallengeDataSource {

    override suspend fun getMemberChallenge(): Response<BaseResponse<GetMemberChallengeResponseDto>> =
        memberChallengeService.getMemberChallenge()

    override suspend fun deleteMemberChallenge(): Response<BaseResponse<Unit>> =
        memberChallengeService.deleteMemberChallenge()

    override suspend fun achieveMemberChallenge(): Response<BaseResponse<Unit>> =
        memberChallengeService.achieveMemberChallenge()

    override suspend fun addMemberChallenge(request: AddMemberChallengeRequestDto): Response<BaseResponse<AddMemberChallengeResponseDto>> =
        memberChallengeService.addMemberChallenge(request)
}