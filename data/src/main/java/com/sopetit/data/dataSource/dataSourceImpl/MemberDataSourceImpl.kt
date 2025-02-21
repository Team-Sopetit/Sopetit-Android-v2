package com.sopetit.data.dataSource.dataSourceImpl

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.dataSource.MemberDataSource
import com.sopetit.data.entity.request.member.CreateMemberRequestDto
import com.sopetit.data.service.MemberService
import retrofit2.Response
import javax.inject.Inject

class MemberDataSourceImpl @Inject constructor(
    private val memberService: MemberService
) : MemberDataSource {

    override suspend fun postCreateMember(request: CreateMemberRequestDto): Response<BaseResponse<Unit>> =
        memberService.createMember(request)
}