package com.sopetit.domain.usecase.memberroutine

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.repository.MemberRoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddDailyMemberRoutineUseCase
    @Inject
    constructor(
        private val memberRoutineRepository: MemberRoutineRepository,
    ) : UseCase<List<Int>, Result<List<Int>>>() {
        override suspend fun invoke(request: List<Int>): Flow<Result<List<Int>>> =
            memberRoutineRepository.addDailyMemberRoutine(request)
    }
