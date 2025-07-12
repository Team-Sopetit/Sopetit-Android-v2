package com.sopetit.domain.usecase.customroutine

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.entity.request.customroutine.CreateCustomRoutineRequestModel
import com.sopetit.domain.entity.response.customroutine.CreateCustomRoutineModel
import com.sopetit.domain.repository.CustomRoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostCreateCustomRoutineUseCase @Inject constructor(
    private val customRoutineRepository: CustomRoutineRepository,
) : UseCase<CreateCustomRoutineRequestModel, Result<CreateCustomRoutineModel>>() {

    override suspend fun invoke(request: CreateCustomRoutineRequestModel): Flow<Result<CreateCustomRoutineModel>> =
        customRoutineRepository.postCreateCustomRoutine(request)
}