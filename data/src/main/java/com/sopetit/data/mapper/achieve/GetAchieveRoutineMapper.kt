package com.sopetit.data.mapper.achieve

import com.sopetit.data.base.BaseMapper
import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.response.achieve.GetAchieveRoutineResponseDto
import com.sopetit.domain.entity.response.achieve.AchieveRoutineItem
import com.sopetit.domain.entity.response.achieve.AchieveRoutineModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object GetAchieveRoutineMapper : BaseMapper() {
    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<GetAchieveRoutineResponseDto>>): Flow<Result<AchieveRoutineModel>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.let { data ->
                    AchieveRoutineModel(
                        id = data.id,
                        name = data.name,
                        routineTotalCount = data.routineTotalCount,
                        routines =
                            data.routines.map { routine ->
                                AchieveRoutineItem(
                                    content = routine.content,
                                    achievedCount = routine.achievedCount,
                                    startedAt = routine.startedAt,
                                )
                            },
                        challengeTotalCount = data.challengeTotalCount,
                        challenges =
                            data.challenges.map { challenge ->
                                AchieveRoutineItem(
                                    content = challenge.content,
                                    achievedCount = challenge.achievedCount,
                                    startedAt = challenge.startedAt,
                                )
                            },
                    )
                } ?: AchieveRoutineModel()
            },
        )
    }
}
