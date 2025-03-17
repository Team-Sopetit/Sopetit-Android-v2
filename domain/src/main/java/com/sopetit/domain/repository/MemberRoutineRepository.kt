package com.sopetit.domain.repository

import com.sopetit.domain.entity.response.memberroutine.AchieveDailyRoutineModel
import com.sopetit.domain.entity.response.memberroutine.MemberDailyRoutineTotalModel
import kotlinx.coroutines.flow.Flow

interface MemberRoutineRepository {
    suspend fun getMemberDailyRoutine(): Flow<Result<MemberDailyRoutineTotalModel>>

    suspend fun deleteMemberDailyRoutine(request: List<Int>): Flow<Result<Unit>>

    suspend fun achieveDailyRoutine(routineId: Int): Flow<Result<AchieveDailyRoutineModel>>
}