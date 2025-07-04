package com.sopetit.data.service

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.base.EndPoints
import com.sopetit.data.entity.request.memberchallenge.AddMemberChallengeRequestDto
import com.sopetit.data.entity.response.memberchallenge.AddMemberChallengeResponseDto
import com.sopetit.data.entity.response.memberchallenge.GetMemberChallengeResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface MemberChallengeService {

    @GET(EndPoints.MemberChallenge.MEMBERCHALLENGE)
    suspend fun getMemberChallenge(): Response<BaseResponse<GetMemberChallengeResponseDto>>

    @DELETE(EndPoints.MemberChallenge.MEMBERCHALLENGE)
    suspend fun deleteMemberChallenge(): Response<BaseResponse<Unit>>

    @PATCH(EndPoints.MemberChallenge.CHALLENGEACHIEVE)
    suspend fun achieveMemberChallenge(): Response<BaseResponse<Unit>>

    @POST(EndPoints.MemberChallenge.MEMBERCHALLENGE)
    suspend fun addMemberChallenge(
        @Body body: AddMemberChallengeRequestDto
    ): Response<BaseResponse<AddMemberChallengeResponseDto>>

    @DELETE(EndPoints.MemberChallenge.ROUTINEHISTORY)
    suspend fun deleteRoutineHistory(
        @Path("historyId") historyId: Int
    ): Response<BaseResponse<Unit>>
}