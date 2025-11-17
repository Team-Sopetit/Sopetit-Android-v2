package com.sopetit.data.dataSource.dataSourceImpl

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.dataSource.MemberRoutineDataSource
import com.sopetit.data.entity.request.memberroutine.AddMemberDailyRoutineRequestDto
import com.sopetit.data.entity.response.memberroutine.AchieveDailyRoutineResponseDto
import com.sopetit.data.entity.response.memberroutine.AddMemberDailyRoutineResponseDto
import com.sopetit.data.entity.response.memberroutine.GetMemberRoutineResponseDto
import com.sopetit.data.service.MemberRoutineService
import retrofit2.Response
import javax.inject.Inject

class MemberRoutineDataSourceImpl
    @Inject
    constructor(
        private val memberRoutineService: MemberRoutineService,
    ) : MemberRoutineDataSource {
        override suspend fun getMemberRoutine(): Response<BaseResponse<GetMemberRoutineResponseDto>> =
            memberRoutineService.getMemberDailyRoutine()

        override suspend fun deleteMemberRoutine(request: List<Int>): Response<BaseResponse<Unit>> =
            memberRoutineService.deleteMemberDailyRoutine(request)

        override suspend fun achieveDailyRoutine(routineId: Int): Response<BaseResponse<AchieveDailyRoutineResponseDto>> =
            memberRoutineService.achieveMemberDailyRoutine(routineId)

        override suspend fun addMemberDailyRoutine(request: AddMemberDailyRoutineRequestDto): Response<BaseResponse<AddMemberDailyRoutineResponseDto>> =
            memberRoutineService.addMemberDailyRoutine(request)

        override suspend fun deleteRoutineHistory(request: Int): Response<BaseResponse<Unit>> =
            memberRoutineService.deleteDailyRoutineHistory(request)
    }
