package com.sopetit.domain.usecase.memo

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.entity.request.memo.MemoWriteRequestModel
import com.sopetit.domain.repository.MemoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostWriteMemoUseCase
    @Inject
    constructor(
        private val memoRepository: MemoRepository,
    ) : UseCase<MemoWriteRequestModel, Result<Int>>() {
        override suspend fun invoke(request: MemoWriteRequestModel): Flow<Result<Int>> =
            memoRepository.postWriteMemo(request)
    }
