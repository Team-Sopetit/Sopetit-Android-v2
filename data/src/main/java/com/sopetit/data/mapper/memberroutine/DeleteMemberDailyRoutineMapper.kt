package com.sopetit.data.mapper.memberroutine

import com.sopetit.data.base.BaseMapper
import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.request.routine.DeleteMemberDailyRoutineRequestDto
import com.sopetit.domain.entity.request.routine.DeleteDailyRoutineRequestModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object DeleteMemberDailyRoutineMapper : BaseMapper() {

    fun DeleteDailyRoutineRequestModel.toDto() = DeleteMemberDailyRoutineRequestDto(
        routines = routines
    )

    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<Unit>>): Flow<Result<Unit>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = {}
        )
    }
}