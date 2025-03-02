package com.sopetit.data.repositoryImpl

import com.sopetit.data.dataSource.MemberRoutineDataSource
import com.sopetit.data.mapper.memberroutine.GetMemberDailyRoutineMapper
import com.sopetit.domain.entity.response.memberroutine.MemberDailyRoutineTotalModel
import com.sopetit.domain.repository.MemberRoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemberRoutineRepositoryImpl @Inject constructor(
    private val memberRoutineDataSource: MemberRoutineDataSource,
) : MemberRoutineRepository {
    override suspend fun getMemberDailyRoutine(): Flow<Result<MemberDailyRoutineTotalModel>> =
        GetMemberDailyRoutineMapper.responseToModel(apiCall = { memberRoutineDataSource.getMemberRoutine() })
}