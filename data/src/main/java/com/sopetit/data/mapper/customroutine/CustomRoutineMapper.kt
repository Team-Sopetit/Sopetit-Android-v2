package com.sopetit.data.mapper.customroutine

import com.sopetit.data.base.BaseMapper
import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.request.customroutine.CustomRoutineRequestDto
import com.sopetit.data.entity.response.createroutine.CustomRoutineResponseDto
import com.sopetit.domain.entity.request.customroutine.CustomRoutineRequestModel
import com.sopetit.domain.entity.response.customroutine.CustomRoutineModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object CustomRoutineMapper : BaseMapper() {
    fun CustomRoutineRequestModel.toDto() =
        CustomRoutineRequestDto(
            content = content,
            themeId = themeId,
            alarmTime = alarmTime,
        )

    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<CustomRoutineResponseDto>>): Flow<Result<CustomRoutineModel>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.let { data ->
                    CustomRoutineModel(
                        id = data.id,
                        content = data.content,
                        themeId = data.themeId,
                        alarmTime = data.alarmTime,
                    )
                } ?: CustomRoutineModel()
            },
        )
    }
}
