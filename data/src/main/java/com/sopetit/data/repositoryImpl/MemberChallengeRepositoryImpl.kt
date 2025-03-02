package com.sopetit.data.repositoryImpl

import com.sopetit.data.dataSource.MemberChallengeDataSource
import com.sopetit.data.mapper.memberchallenge.GetMemberChallengeMapper
import com.sopetit.domain.entity.response.memberchallenge.MemberChallengeModel
import com.sopetit.domain.repository.MemberChallengeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemberChallengeRepositoryImpl @Inject constructor(
    private val memberChallengeDataSource: MemberChallengeDataSource,
) : MemberChallengeRepository {

    override suspend fun getMemberChallenge(): Flow<Result<MemberChallengeModel>> =
        GetMemberChallengeMapper.responseToModel(apiCall = { memberChallengeDataSource.getMemberChallenge() })
}