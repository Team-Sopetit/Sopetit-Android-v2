package com.sopetit.onboarding.themechoice

import androidx.lifecycle.viewModelScope
import com.sopetit.domain.usecase.theme.GetThemeListUseCase
import com.sopetit.ui.base.BaseViewModel
import com.sopetit.ui.base.PageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ThemeChoiceViewModel @Inject constructor(
    private val getThemeListUseCase: GetThemeListUseCase
) : BaseViewModel<PageState.Default>(
    PageState.Default
) {

    init {
        initGetThemeList()
    }

    private fun initGetThemeList() {
        viewModelScope.launch {
            getThemeListUseCase(request = Unit).collect {
                resultResponse(it, {
                    Timber.d("[온보딩] 테마 목록 조회 -> $it")
                })
            }
        }
    }
}