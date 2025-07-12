package com.sopetit.data.mapper.customroutine

import com.sopetit.data.base.BaseMapper
import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.request.customroutine.CreateCustomRoutineRequestDto
import com.sopetit.data.entity.response.createroutine.CreateCustomRoutineResponseDto
import com.sopetit.domain.entity.request.customroutine.CreateCustomRoutineRequestModel
import com.sopetit.domain.entity.response.customroutine.CreateCustomRoutineModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object CreateCustomRoutineMapper : BaseMapper() {
    fun CreateCustomRoutineRequestModel.toDto() =
        CreateCustomRoutineRequestDto(
            content = content,
            themeId = themeId,
            alarmTime = alarmTime
        )

    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<CreateCustomRoutineResponseDto>>): Flow<Result<CreateCustomRoutineModel>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.let { data ->
                    CreateCustomRoutineModel(
                        id = data.id,
                        content = data.content,
                        themeId = data.themeId,
                        alarmTime = data.alarmTime
                    )
                } ?: CreateCustomRoutineModel()
            }
        )
    }
}