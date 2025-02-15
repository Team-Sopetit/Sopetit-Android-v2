package com.sopetit.domain.repository

import com.sopetit.domain.entity.response.theme.ThemeListModel
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    suspend fun themeList(): Flow<Result<ThemeListModel>>
}