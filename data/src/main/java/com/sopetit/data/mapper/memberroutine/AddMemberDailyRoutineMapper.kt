package com.sopetit.data.mapper.memberroutine

import com.sopetit.data.base.BaseMapper
import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.request.memberroutine.AddMemberDailyRoutineRequestDto
import com.sopetit.data.entity.response.memberroutine.AddMemberDailyRoutineResponseDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object AddMemberDailyRoutineMapper : BaseMapper() {
    fun listToDto(request: List<Int>) = AddMemberDailyRoutineRequestDto(request)

    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<AddMemberDailyRoutineResponseDto>>): Flow<Result<List<Int>>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.ids ?: emptyList()
            },
        )
    }
}
