package com.sopetit.data.mapper.member

import com.sopetit.data.base.BaseMapper
import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.request.member.CreateMemberRequestDto
import com.sopetit.domain.entity.request.CreateMemberModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object CreateMemberMapper : BaseMapper() {
    fun CreateMemberModel.toDto() =
        CreateMemberRequestDto(
            name = dollName,
            dollType = dollType.value,
            routines = selectedRoutineIdList,
        )

    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<Unit>>): Flow<Result<Unit>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = {},
        )
    }
}
