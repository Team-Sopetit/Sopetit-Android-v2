package com.sopetit.data.dataSource

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.response.routine.ChallengeResponseDto
import com.sopetit.data.entity.response.routine.DailyRoutineListResponseDto
import com.sopetit.data.entity.response.routine.DailyThemeRoutineResponseDto
import retrofit2.Response

interface RoutineDataSource {
    suspend fun getDailyRoutine(request: List<Int>): Response<BaseResponse<DailyRoutineListResponseDto>>
    suspend fun getDailyThemeRoutine(request: Int): Response<BaseResponse<DailyThemeRoutineResponseDto>>
    suspend fun getChallengeRoutine(request: Int): Response<BaseResponse<ChallengeResponseDto>>
}