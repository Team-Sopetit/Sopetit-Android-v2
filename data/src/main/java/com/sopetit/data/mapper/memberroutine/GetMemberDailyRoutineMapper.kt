package com.sopetit.data.mapper.memberroutine

import com.sopetit.data.base.BaseMapper
import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.response.memberroutine.GetMemberRoutineResponseDto
import com.sopetit.domain.entity.response.memberroutine.MemberDailyRoutineListItemModel
import com.sopetit.domain.entity.response.memberroutine.MemberDailyRoutineListModel
import com.sopetit.domain.entity.response.memberroutine.MemberDailyRoutineTotalModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object GetMemberDailyRoutineMapper: BaseMapper() {

    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<GetMemberRoutineResponseDto>>): Flow<Result<MemberDailyRoutineTotalModel>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.let { data ->
                    MemberDailyRoutineTotalModel(
                        routines = data.routines.map { routineList ->
                            MemberDailyRoutineListModel(
                                themeId = routineList.themeId,
                                themeName = routineList.themeName,
                                routines = routineList.routines.map { listItem ->
                                    MemberDailyRoutineListItemModel(
                                        routineId = listItem.routineId,
                                        content = listItem.content,
                                        achieveCount = listItem.achieveCount,
                                        isAchieve = listItem.isAchieve
                                    )
                                }
                            )
                        }
                    )
                } ?: MemberDailyRoutineTotalModel()
            }
        )
    }
}