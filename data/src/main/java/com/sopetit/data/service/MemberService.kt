package com.sopetit.data.service

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.base.EndPoints
import com.sopetit.data.entity.request.member.CreateMemberRequestDto
import com.sopetit.data.entity.request.member.PostFcmRequestDto
import com.sopetit.data.entity.response.member.CottonResponseDto
import com.sopetit.data.entity.response.member.GetMemberResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface MemberService {
    @POST(EndPoints.Member.MEMBER)
    suspend fun createMember(
        @Body body: CreateMemberRequestDto,
    ): Response<BaseResponse<Unit>>

    @GET(EndPoints.Member.MEMBER)
    suspend fun getMember(): Response<BaseResponse<GetMemberResponseDto>>

    @POST(EndPoints.Member.FCM)
    suspend fun postFcm(
        @Body body: PostFcmRequestDto,
    ): Response<BaseResponse<Unit>>

    @PATCH(EndPoints.Member.COTTON)
    suspend fun patchCotton(
        @Path("cottonType") cottonType: String,
    ): Response<BaseResponse<CottonResponseDto>>
}
