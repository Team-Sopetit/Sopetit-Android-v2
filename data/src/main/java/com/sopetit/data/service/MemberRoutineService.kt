package com.sopetit.data.service

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.base.EndPoints
import com.sopetit.data.entity.request.routine.DeleteMemberDailyRoutineRequestDto
import com.sopetit.data.entity.response.memberroutine.GetMemberRoutineResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET

interface MemberRoutineService {

    @GET(EndPoints.MemberRoutine.MEMBERROUTINE2)
    suspend fun getMemberDailyRoutine(): Response<BaseResponse<GetMemberRoutineResponseDto>>

    @DELETE(EndPoints.MemberRoutine.MEMBERROUTINE)
    suspend fun deleteMemberDailyRoutine(
        @Body body: DeleteMemberDailyRoutineRequestDto,
    ): Response<BaseResponse<Unit>>
}