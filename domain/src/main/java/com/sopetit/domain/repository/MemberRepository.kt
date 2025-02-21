package com.sopetit.domain.repository

import com.sopetit.domain.entity.request.CreateMemberModel
import kotlinx.coroutines.flow.Flow

interface MemberRepository {
    suspend fun postCreateMember(request: CreateMemberModel): Flow<Result<Unit>>
}