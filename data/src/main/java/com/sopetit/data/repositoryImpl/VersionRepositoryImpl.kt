package com.sopetit.data.repositoryImpl

import com.sopetit.data.dataSource.VersionDataSource
import com.sopetit.data.mapper.version.GetVersionMapper
import com.sopetit.domain.entity.response.version.VersionModel
import com.sopetit.domain.repository.VersionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VersionRepositoryImpl
    @Inject
    constructor(
        private val versionDataSource: VersionDataSource,
    ) : VersionRepository {
        override suspend fun getVersion(): Flow<Result<VersionModel>> =
            GetVersionMapper.responseToModel(apiCall = { versionDataSource.getVersion() })
    }
