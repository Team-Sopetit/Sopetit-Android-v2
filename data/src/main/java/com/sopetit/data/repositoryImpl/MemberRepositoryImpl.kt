package com.sopetit.data.repositoryImpl

import com.sopetit.data.dataSource.MemberDataSource
import com.sopetit.data.mapper.member.CreateMemberMapper
import com.sopetit.data.mapper.member.CreateMemberMapper.toDto
import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val memberDataSource: MemberDataSource
) : MemberRepository {
    override suspend fun postCreateMember(request: CreateMemberModel): Flow<Result<Unit>> =
        CreateMemberMapper.responseToModel(apiCall = { memberDataSource.postCreateMember(request.toDto()) })
}