package com.sopetit.data.dataSource

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.request.member.CreateMemberRequestDto
import retrofit2.Response

interface MemberDataSource {
    suspend fun postCreateMember(request: CreateMemberRequestDto): Response<BaseResponse<Unit>>
}