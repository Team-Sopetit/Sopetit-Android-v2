package com.sopetit.data.dataSource

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.request.memberroutine.AddMemberDailyRoutineRequestDto
import com.sopetit.data.entity.response.memberroutine.AchieveDailyRoutineResponseDto
import com.sopetit.data.entity.response.memberroutine.AddMemberDailyRoutineResponseDto
import com.sopetit.data.entity.response.memberroutine.GetMemberRoutineResponseDto
import retrofit2.Response

interface MemberRoutineDataSource {
    suspend fun getMemberRoutine(): Response<BaseResponse<GetMemberRoutineResponseDto>>

    suspend fun deleteMemberRoutine(request: List<Int>): Response<BaseResponse<Unit>>

    suspend fun achieveDailyRoutine(routineId: Int): Response<BaseResponse<AchieveDailyRoutineResponseDto>>

    suspend fun addMemberDailyRoutine(request: AddMemberDailyRoutineRequestDto): Response<BaseResponse<AddMemberDailyRoutineResponseDto>>

    suspend fun deleteRoutineHistory(request: Int): Response<BaseResponse<Unit>>
}
