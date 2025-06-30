package com.sopetit.data.repositoryImpl

import com.sopetit.data.dataSource.MemoDataSource
import com.sopetit.data.mapper.memo.MemoWriteMapper
import com.sopetit.data.mapper.memo.MemoWriteMapper.toDto
import com.sopetit.domain.entity.request.memo.MemoWriteRequestModel
import com.sopetit.domain.repository.MemoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemoRepositoryImpl @Inject constructor(
    private val memoDataSource: MemoDataSource,
) : MemoRepository {

    override suspend fun postWriteMemo(request: MemoWriteRequestModel): Flow<Result<Int>> =
        MemoWriteMapper.responseToModel(apiCall = { memoDataSource.postWriteMemo(request.toDto()) })
}