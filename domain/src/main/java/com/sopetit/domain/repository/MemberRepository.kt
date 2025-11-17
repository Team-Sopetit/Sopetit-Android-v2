package com.sopetit.domain.repository

import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.domain.entity.response.member.GetMemberModel
import kotlinx.coroutines.flow.Flow

interface MemberRepository {
    suspend fun postCreateMember(request: CreateMemberModel): Flow<Result<Unit>>

    suspend fun getMember(): Flow<Result<GetMemberModel>>

    suspend fun postFcmToken(): Flow<Result<Unit>>

    suspend fun patchCotton(request: String): Flow<Result<Int>>
}
