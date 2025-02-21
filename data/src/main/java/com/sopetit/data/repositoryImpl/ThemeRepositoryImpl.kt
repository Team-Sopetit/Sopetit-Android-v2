package com.sopetit.data.repositoryImpl

import com.sopetit.data.dataSource.ThemeDataSource
import com.sopetit.data.mapper.theme.ThemeListMapper
import com.sopetit.domain.entity.response.theme.ThemeListModel
import com.sopetit.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ThemeRepositoryImpl @Inject constructor(
    private val themeDataSource: ThemeDataSource
) : ThemeRepository {

    override suspend fun themeList(): Flow<Result<ThemeListModel>> =
        ThemeListMapper.responseToModel(apiCall = { themeDataSource.themeList() })

}