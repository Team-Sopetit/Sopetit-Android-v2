package com.sopetit.data.dataSource.dataSourceImpl

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.dataSource.MemberRoutineDataSource
import com.sopetit.data.entity.response.memberroutine.GetMemberRoutineResponseDto
import com.sopetit.data.service.MemberRoutineService
import retrofit2.Response
import javax.inject.Inject

class MemberRoutineDataSourceImpl @Inject constructor(
    private val memberRoutineService: MemberRoutineService,
) : MemberRoutineDataSource {

    override suspend fun getMemberRoutine(): Response<BaseResponse<GetMemberRoutineResponseDto>> =
        memberRoutineService.getMemberDailyRoutine()

    override suspend fun deleteMemberRoutine(request: List<Int>): Response<BaseResponse<Unit>> =
        memberRoutineService.deleteMemberDailyRoutine(request)
}