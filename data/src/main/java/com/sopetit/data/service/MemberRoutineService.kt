package com.sopetit.data.service

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.base.EndPoints
import com.sopetit.data.entity.response.memberroutine.AchieveDailyRoutineResponseDto
import com.sopetit.data.entity.response.memberroutine.GetMemberRoutineResponseDto
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface MemberRoutineService {

    @GET(EndPoints.MemberRoutine.MEMBERROUTINE2)
    suspend fun getMemberDailyRoutine(): Response<BaseResponse<GetMemberRoutineResponseDto>>

    @DELETE(EndPoints.MemberRoutine.MEMBERROUTINE)
    suspend fun deleteMemberDailyRoutine(
        @Query("routines") body: List<Int>,
    ): Response<BaseResponse<Unit>>

    @PATCH(EndPoints.MemberRoutine.ROUTINEACHIEVE)
    suspend fun achieveMemberDailyRoutine(
        @Path("routineId") routineId: Int
    ): Response<BaseResponse<AchieveDailyRoutineResponseDto>>
}