package com.sopetit.domain.repository

import com.sopetit.domain.entity.response.memberchallenge.MemberChallengeModel
import kotlinx.coroutines.flow.Flow

interface MemberChallengeRepository {
    suspend fun getMemberChallenge(): Flow<Result<MemberChallengeModel>>
}