package com.sopetit.data.dataSource

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.response.memberroutine.GetMemberRoutineResponseDto
import retrofit2.Response

interface MemberRoutineDataSource {
    suspend fun getMemberRoutine(): Response<BaseResponse<GetMemberRoutineResponseDto>>
}