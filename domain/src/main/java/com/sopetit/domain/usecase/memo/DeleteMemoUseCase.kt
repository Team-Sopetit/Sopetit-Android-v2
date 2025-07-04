package com.sopetit.domain.usecase.memo

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.repository.MemoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteMemoUseCase @Inject constructor(
    private val memoRepository: MemoRepository,
) : UseCase<Int, Result<Unit>>() {

    override suspend fun invoke(request: Int): Flow<Result<Unit>> =
        memoRepository.deleteMemo(request)
}