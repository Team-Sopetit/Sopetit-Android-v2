package com.sopetit.data.dataSource

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.request.member.CreateMemberRequestDto
import com.sopetit.data.entity.request.member.PostFcmRequestDto
import com.sopetit.data.entity.response.member.GetMemberResponseDto
import retrofit2.Response

interface MemberDataSource {
    suspend fun postCreateMember(request: CreateMemberRequestDto): Response<BaseResponse<Unit>>
    suspend fun getMember(): Response<BaseResponse<GetMemberResponseDto>>

    suspend fun postFcmToken(request: PostFcmRequestDto): Response<BaseResponse<Unit>>
}