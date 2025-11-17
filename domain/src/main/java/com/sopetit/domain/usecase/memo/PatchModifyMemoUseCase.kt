package com.sopetit.domain.usecase.memo

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.entity.response.memo.MemoActionModel
import com.sopetit.domain.repository.MemoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PatchModifyMemoUseCase
    @Inject
    constructor(
        private val memoRepository: MemoRepository,
    ) : UseCase<MemoActionModel, Result<Unit>>() {
        override suspend fun invoke(request: MemoActionModel): Flow<Result<Unit>> =
            memoRepository.modifyMemo(request)
    }
