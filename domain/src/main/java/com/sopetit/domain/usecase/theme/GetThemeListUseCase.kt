package com.sopetit.domain.usecase.theme

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.entity.response.theme.ThemeListModel
import com.sopetit.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetThemeListUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) : UseCase<Unit, Result<ThemeListModel>>() {

    override suspend fun invoke(request: Unit): Flow<Result<ThemeListModel>> =
        themeRepository.themeList()
}