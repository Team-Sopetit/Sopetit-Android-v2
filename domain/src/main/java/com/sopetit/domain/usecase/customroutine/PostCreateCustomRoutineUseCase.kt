package com.sopetit.domain.usecase.customroutine

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.entity.request.customroutine.CustomRoutineRequestModel
import com.sopetit.domain.entity.response.customroutine.CustomRoutineModel
import com.sopetit.domain.repository.CustomRoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostCreateCustomRoutineUseCase @Inject constructor(
    private val customRoutineRepository: CustomRoutineRepository,
) : UseCase<CustomRoutineRequestModel, Result<CustomRoutineModel>>() {

    override suspend fun invoke(request: CustomRoutineRequestModel): Flow<Result<CustomRoutineModel>> =
        customRoutineRepository.postCreateCustomRoutine(request)
}