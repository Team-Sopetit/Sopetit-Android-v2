package com.sopetit.data.mapper.routine

import com.sopetit.data.base.BaseMapper
import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.response.routine.DailyRoutineListResponseDto
import com.sopetit.domain.entity.request.routine.DailyRoutineListRequestModel
import com.sopetit.domain.entity.response.routine.DailyRoutineListItemModel
import com.sopetit.domain.entity.response.routine.DailyRoutineListModel
import com.sopetit.domain.entity.response.routine.DailyRoutineListThemeTotalModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object DailyRoutineMapper : BaseMapper() {
    fun DailyRoutineListRequestModel.toDto() = themeIdList

    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<DailyRoutineListResponseDto>>): Flow<Result<DailyRoutineListThemeTotalModel>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.let { data ->
                    DailyRoutineListThemeTotalModel(
                        themeTotalList =
                            data.themes.map { routineList ->
                                DailyRoutineListModel(
                                    themeId = routineList.themeId,
                                    routines =
                                        routineList.routines.map { listItem ->
                                            DailyRoutineListItemModel(
                                                routineId = listItem.routineId,
                                                content = listItem.content,
                                            )
                                        },
                                )
                            },
                    )
                } ?: DailyRoutineListThemeTotalModel()
            },
        )
    }
}
