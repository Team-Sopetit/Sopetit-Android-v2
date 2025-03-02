package com.sopetit.data.service

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.base.EndPoints
import com.sopetit.data.entity.response.memberroutine.GetMemberRoutineResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface MemberRoutineService {

    @GET(EndPoints.MemberRoutine.MEMBERROUTINE)
    suspend fun getMemberDailyRoutine(): Response<BaseResponse<GetMemberRoutineResponseDto>>
}