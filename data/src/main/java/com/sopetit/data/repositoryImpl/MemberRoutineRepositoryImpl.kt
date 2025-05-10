package com.sopetit.data.repositoryImpl

import com.sopetit.data.dataSource.MemberRoutineDataSource
import com.sopetit.data.mapper.memberroutine.AchieveDailyRoutineMapper
import com.sopetit.data.mapper.memberroutine.DeleteMemberDailyRoutineMapper
import com.sopetit.data.mapper.memberroutine.GetMemberDailyRoutineMapper
import com.sopetit.domain.entity.response.memberroutine.AchieveDailyRoutineModel
import com.sopetit.domain.entity.response.memberroutine.MemberDailyRoutineTotalModel
import com.sopetit.domain.repository.MemberRoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemberRoutineRepositoryImpl @Inject constructor(
    private val memberRoutineDataSource: MemberRoutineDataSource,
) : MemberRoutineRepository {
    override suspend fun getMemberDailyRoutine(): Flow<Result<MemberDailyRoutineTotalModel>> =
        GetMemberDailyRoutineMapper.responseToModel(apiCall = { memberRoutineDataSource.getMemberRoutine() })

    override suspend fun deleteMemberDailyRoutine(request: List<Int>): Flow<Result<Unit>> =
        DeleteMemberDailyRoutineMapper.responseToModel(apiCall = {
            memberRoutineDataSource.deleteMemberRoutine(
                request
            )
        })

    override suspend fun achieveDailyRoutine(routineId: Int): Flow<Result<AchieveDailyRoutineModel>> =
        AchieveDailyRoutineMapper.responseToModel(apiCall = {
            memberRoutineDataSource.achieveDailyRoutine(
                routineId
            )
        })
}