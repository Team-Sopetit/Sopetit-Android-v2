package com.sopetit.data.mapper.routine

import com.sopetit.data.base.BaseMapper
import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.response.routine.DailyThemeRoutineResponseDto
import com.sopetit.domain.entity.response.routine.DailyRoutineListItemModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object DailyThemeRoutineMapper: BaseMapper() {

    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<DailyThemeRoutineResponseDto>>): Flow<Result<List<DailyRoutineListItemModel>>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.let { data ->
                    data.routines.map { routine ->
                        DailyRoutineListItemModel(
                            routineId = routine.routineId,
                            content = routine.content
                        )
                    }
                } ?: listOf(DailyRoutineListItemModel())
            }
        )
    }
}