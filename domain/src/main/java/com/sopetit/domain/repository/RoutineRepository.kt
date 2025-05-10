package com.sopetit.domain.repository

import com.sopetit.domain.entity.request.routine.DailyRoutineListRequestModel
import com.sopetit.domain.entity.response.routine.ChallengeItemModel
import com.sopetit.domain.entity.response.routine.DailyRoutineListItemModel
import com.sopetit.domain.entity.response.routine.DailyRoutineListThemeTotalModel
import kotlinx.coroutines.flow.Flow

interface RoutineRepository {
    suspend fun dailyRoutineList(request: DailyRoutineListRequestModel): Flow<Result<DailyRoutineListThemeTotalModel>>
    suspend fun getDailyThemeRoutineList(request: Int): Flow<Result<List<DailyRoutineListItemModel>>>
    suspend fun getChallenge(request: Int): Flow<Result<List<ChallengeItemModel>>>
}