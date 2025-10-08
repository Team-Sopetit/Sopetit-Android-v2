package com.sopetit.domain.repository

import com.sopetit.domain.entity.response.version.VersionModel
import kotlinx.coroutines.flow.Flow

interface VersionRepository {
    suspend fun getVersion(): Flow<Result<VersionModel>>
}