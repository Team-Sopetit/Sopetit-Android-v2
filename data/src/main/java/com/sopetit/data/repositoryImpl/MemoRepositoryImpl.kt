package com.sopetit.data.repositoryImpl

import com.sopetit.data.dataSource.MemoDataSource
import com.sopetit.data.entity.request.memo.MemoModifyRequestDto
import com.sopetit.data.mapper.DefaultUnitMapper
import com.sopetit.data.mapper.memo.MemoWriteMapper
import com.sopetit.data.mapper.memo.MemoWriteMapper.toDto
import com.sopetit.domain.entity.request.memo.MemoWriteRequestModel
import com.sopetit.domain.entity.response.memo.MemoActionModel
import com.sopetit.domain.repository.MemoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemoRepositoryImpl
    @Inject
    constructor(
        private val memoDataSource: MemoDataSource,
    ) : MemoRepository {
        override suspend fun postWriteMemo(request: MemoWriteRequestModel): Flow<Result<Int>> =
            MemoWriteMapper.responseToModel(apiCall = { memoDataSource.postWriteMemo(request.toDto()) })

        override suspend fun deleteMemo(request: Int): Flow<Result<Unit>> =
            DefaultUnitMapper.responseToModel(apiCall = { memoDataSource.deleteMemo(request) })

        override suspend fun modifyMemo(request: MemoActionModel): Flow<Result<Unit>> =
            DefaultUnitMapper.responseToModel(apiCall = {
                memoDataSource.modifyMemo(
                    request.memoId,
                    MemoModifyRequestDto(request.content),
                )
            })
    }
