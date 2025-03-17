package com.sopetit.data.dataSource

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.request.routine.DeleteMemberDailyRoutineRequestDto
import com.sopetit.data.entity.response.memberroutine.GetMemberRoutineResponseDto
import retrofit2.Response

interface MemberRoutineDataSource {
    suspend fun getMemberRoutine(): Response<BaseResponse<GetMemberRoutineResponseDto>>

    suspend fun deleteMemberRoutine(request: DeleteMemberDailyRoutineRequestDto): Response<BaseResponse<Unit>>
}