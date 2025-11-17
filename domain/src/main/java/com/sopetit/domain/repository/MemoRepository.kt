package com.sopetit.domain.repository

import com.sopetit.domain.entity.request.memo.MemoWriteRequestModel
import com.sopetit.domain.entity.response.memo.MemoActionModel
import kotlinx.coroutines.flow.Flow

interface MemoRepository {
    suspend fun postWriteMemo(request: MemoWriteRequestModel): Flow<Result<Int>>

    suspend fun deleteMemo(request: Int): Flow<Result<Unit>>

    suspend fun modifyMemo(request: MemoActionModel): Flow<Result<Unit>>
}
