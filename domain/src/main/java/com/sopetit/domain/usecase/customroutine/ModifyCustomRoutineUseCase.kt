package com.sopetit.domain.usecase.customroutine

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.entity.request.customroutine.ModifyCustomRoutineRequestModel
import com.sopetit.domain.entity.response.customroutine.CustomRoutineModel
import com.sopetit.domain.repository.CustomRoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ModifyCustomRoutineUseCase
    @Inject
    constructor(
        private val customRoutineRepository: CustomRoutineRepository,
    ) : UseCase<ModifyCustomRoutineRequestModel, Result<CustomRoutineModel>>() {
        override suspend fun invoke(request: ModifyCustomRoutineRequestModel): Flow<Result<CustomRoutineModel>> =
            customRoutineRepository.modifyCreateCustomRoutine(request)
    }
