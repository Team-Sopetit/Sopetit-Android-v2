package com.sopetit.domain.usecase.version

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.entity.response.version.VersionModel
import com.sopetit.domain.repository.VersionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVersionUseCase
    @Inject
    constructor(
        private val versionRepository: VersionRepository,
    ) : UseCase<Unit, Result<VersionModel>>() {
        override suspend fun invoke(request: Unit): Flow<Result<VersionModel>> {
            return versionRepository.getVersion()
        }
    }
