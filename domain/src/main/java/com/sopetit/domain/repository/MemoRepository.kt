package com.sopetit.domain.repository

import com.sopetit.domain.entity.request.memo.MemoWriteRequestModel
import kotlinx.coroutines.flow.Flow

interface MemoRepository {
    suspend fun postWriteMemo(request: MemoWriteRequestModel): Flow<Result<Int>>
}