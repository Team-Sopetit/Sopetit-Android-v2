package com.sopetit.data.dataSource.dataSourceImpl

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.dataSource.MemberDataSource
import com.sopetit.data.entity.request.member.CreateMemberRequestDto
import com.sopetit.data.entity.request.member.PostFcmRequestDto
import com.sopetit.data.entity.response.member.CottonResponseDto
import com.sopetit.data.entity.response.member.GetMemberResponseDto
import com.sopetit.data.service.MemberService
import retrofit2.Response
import javax.inject.Inject

class MemberDataSourceImpl
    @Inject
    constructor(
        private val memberService: MemberService,
    ) : MemberDataSource {
        override suspend fun postCreateMember(request: CreateMemberRequestDto): Response<BaseResponse<Unit>> =
            memberService.createMember(request)

        override suspend fun getMember(): Response<BaseResponse<GetMemberResponseDto>> =
            memberService.getMember()

        override suspend fun postFcmToken(request: PostFcmRequestDto): Response<BaseResponse<Unit>> =
            memberService.postFcm(request)

        override suspend fun patchCotton(request: String): Response<BaseResponse<CottonResponseDto>> =
            memberService.patchCotton(request)
    }
