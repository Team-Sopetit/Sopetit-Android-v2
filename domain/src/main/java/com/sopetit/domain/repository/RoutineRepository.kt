package com.sopetit.domain.repository

import com.sopetit.domain.entity.request.routine.DailyRoutineListRequestModel
import com.sopetit.domain.entity.response.routine.ChallengeItemModel
import com.sopetit.domain.entity.response.routine.DailyRoutineListThemeTotalModel
import com.sopetit.domain.entity.response.routine.DailyThemeRoutineItemModel
import kotlinx.coroutines.flow.Flow

interface RoutineRepository {
    suspend fun dailyRoutineList(request: DailyRoutineListRequestModel): Flow<Result<DailyRoutineListThemeTotalModel>>

    suspend fun getDailyThemeRoutineList(request: Int): Flow<Result<List<DailyThemeRoutineItemModel>>>

    suspend fun getChallenge(request: Int): Flow<Result<List<ChallengeItemModel>>>
}
