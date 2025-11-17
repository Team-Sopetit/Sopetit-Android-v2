package com.sopetit.data.repositoryImpl

import com.sopetit.data.dataSource.MemberChallengeDataSource
import com.sopetit.data.mapper.DefaultUnitMapper
import com.sopetit.data.mapper.memberchallenge.AchieveMemberChallengeMapper
import com.sopetit.data.mapper.memberchallenge.AddMemberChallengeMapper
import com.sopetit.data.mapper.memberchallenge.DeleteMemberChallengeMapper
import com.sopetit.data.mapper.memberchallenge.GetMemberChallengeMapper
import com.sopetit.domain.entity.response.memberchallenge.MemberChallengeModel
import com.sopetit.domain.repository.MemberChallengeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemberChallengeRepositoryImpl
    @Inject
    constructor(
        private val memberChallengeDataSource: MemberChallengeDataSource,
    ) : MemberChallengeRepository {
        override suspend fun getMemberChallenge(): Flow<Result<MemberChallengeModel>> =
            GetMemberChallengeMapper.responseToModel(apiCall = { memberChallengeDataSource.getMemberChallenge() })

        override suspend fun deleteMemberChallenge(): Flow<Result<Unit>> =
            DeleteMemberChallengeMapper.responseToModel(apiCall = { memberChallengeDataSource.deleteMemberChallenge() })

        override suspend fun achieveMemberChallenge(): Flow<Result<Unit>> =
            AchieveMemberChallengeMapper.responseToModel(apiCall = { memberChallengeDataSource.achieveMemberChallenge() })

        override suspend fun addMemberChallenge(request: Int): Flow<Result<Int>> =
            AddMemberChallengeMapper.responseToModel(apiCall = {
                memberChallengeDataSource.addMemberChallenge(
                    AddMemberChallengeMapper.intToDto(request),
                )
            })

        override suspend fun deleteChallengeHistory(request: Int): Flow<Result<Unit>> =
            DefaultUnitMapper.responseToModel(apiCall = { memberChallengeDataSource.deleteChallengeHistory(request) })
    }
