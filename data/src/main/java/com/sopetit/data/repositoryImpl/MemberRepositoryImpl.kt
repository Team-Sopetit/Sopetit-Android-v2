package com.sopetit.data.repositoryImpl

import com.sopetit.data.dataSource.MemberDataSource
import com.sopetit.data.dataStore.LocalDataStore
import com.sopetit.data.entity.request.member.PostFcmRequestDto
import com.sopetit.data.mapper.member.CreateMemberMapper
import com.sopetit.data.mapper.member.CreateMemberMapper.toDto
import com.sopetit.data.mapper.member.GetMemberMapper
import com.sopetit.data.mapper.member.PatchCottonMapper
import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.domain.entity.response.member.GetMemberModel
import com.sopetit.domain.repository.MemberRepository
import com.sopetit.firebase.fcmtoken.FcmTokenProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MemberRepositoryImpl
    @Inject
    constructor(
        private val memberDataSource: MemberDataSource,
        private val localDataStore: LocalDataStore,
        private val fcmTokenProvider: FcmTokenProvider,
    ) : MemberRepository {
        override suspend fun postCreateMember(request: CreateMemberModel): Flow<Result<Unit>> =
            CreateMemberMapper.responseToModel(apiCall = { memberDataSource.postCreateMember(request.toDto()) })

        override suspend fun getMember(): Flow<Result<GetMemberModel>> =
            GetMemberMapper.responseToModel(apiCall = { memberDataSource.getMember() })

        override suspend fun postFcmToken(): Flow<Result<Unit>> =
            flow {
                localDataStore.saveFcmToken(fcmTokenProvider.getFcmToken())
                memberDataSource.postFcmToken(
                    PostFcmRequestDto(fcmTokenProvider.getFcmToken()),
                )
            }

        override suspend fun patchCotton(request: String): Flow<Result<Int>> =
            PatchCottonMapper.responseToModel(apiCall = { memberDataSource.patchCotton(request) })
    }
