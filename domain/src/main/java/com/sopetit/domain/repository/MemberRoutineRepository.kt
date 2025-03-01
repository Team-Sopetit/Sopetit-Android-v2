package com.sopetit.domain.repository

import com.sopetit.domain.entity.response.memberroutine.MemberDailyRoutineTotalModel
import kotlinx.coroutines.flow.Flow

interface MemberRoutineRepository {
    suspend fun getMemberDailyRoutine(): Flow<Result<MemberDailyRoutineTotalModel>>
}