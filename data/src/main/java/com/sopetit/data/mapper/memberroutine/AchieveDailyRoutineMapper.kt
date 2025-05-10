package com.sopetit.data.mapper.memberroutine

import com.sopetit.data.base.BaseMapper
import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.response.memberroutine.AchieveDailyRoutineResponseDto
import com.sopetit.domain.entity.response.memberroutine.AchieveDailyRoutineModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object AchieveDailyRoutineMapper : BaseMapper() {

    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<AchieveDailyRoutineResponseDto>>): Flow<Result<AchieveDailyRoutineModel>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.let { data ->
                    AchieveDailyRoutineModel(
                        routineId = data.routineId,
                        isAchieve = data.isAchieve,
                        achieveCount = data.achieveCount,
                        hasCotton = data.hasCotton
                    )
                } ?: AchieveDailyRoutineModel()
            }
        )
    }
}