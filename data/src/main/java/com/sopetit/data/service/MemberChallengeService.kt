package com.sopetit.data.service

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.base.EndPoints
import com.sopetit.data.entity.response.memberchallenge.GetMemberChallengeResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface MemberChallengeService {

    @GET(EndPoints.MemberChallenge.MEMBERCHALLENGE)
    suspend fun getMemberChallenge(): Response<BaseResponse<GetMemberChallengeResponseDto>>
}