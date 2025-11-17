package com.sopetit.domain.usecase.customroutine

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.repository.CustomRoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteCustomRoutineUseCase
    @Inject
    constructor(
        private val customRoutineRepository: CustomRoutineRepository,
    ) : UseCase<Int, Result<Unit>>() {
        override suspend fun invoke(request: Int): Flow<Result<Unit>> =
            customRoutineRepository.deleteCustomRoutine(request)
    }
