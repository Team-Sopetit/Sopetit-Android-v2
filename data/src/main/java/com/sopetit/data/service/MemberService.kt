package com.sopetit.data.service

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.base.EndPoints
import com.sopetit.data.entity.request.member.CreateMemberRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MemberService {

    @POST(EndPoints.Member.MEMBER)
    suspend fun createMember(
        @Body body: CreateMemberRequestDto
    ): Response<BaseResponse<Unit>>
}