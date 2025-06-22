package com.sopetit.data.mapper.routine

import com.sopetit.data.base.BaseMapper
import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.response.routine.DailyThemeRoutineResponseDto
import com.sopetit.domain.entity.response.routine.DailyThemeRoutineItemModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object DailyThemeRoutineMapper: BaseMapper() {

    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<DailyThemeRoutineResponseDto>>): Flow<Result<List<DailyThemeRoutineItemModel>>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.let { data ->
                    data.routines.map { routine ->
                        DailyThemeRoutineItemModel(
                            id = routine.id,
                            content = routine.content,
                            existedInMember = routine.existedInMember
                        )
                    }
                } ?: listOf(DailyThemeRoutineItemModel())
            }
        )
    }
}