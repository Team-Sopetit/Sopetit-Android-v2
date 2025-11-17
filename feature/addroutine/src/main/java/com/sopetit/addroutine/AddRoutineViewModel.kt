package com.sopetit.addroutine

import androidx.lifecycle.viewModelScope
import com.sopetit.domain.entity.response.theme.ThemeListModel
import com.sopetit.domain.usecase.theme.GetThemeListUseCase
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddRoutineViewModel
    @Inject
    constructor(
        private val getThemeListUseCase: GetThemeListUseCase,
    ) : BaseViewModel<AddRoutinePageState>(
            AddRoutinePageState(),
        ) {
        init {
            initSetRoutineThemeList()
        }

        private fun initSetRoutineThemeList() {
            viewModelScope.launch {
                getThemeListUseCase(request = Unit).collect {
                    resultResponse(it, ::onSuccessGetRoutineThemeList)
                }
            }
        }

        private fun onSuccessGetRoutineThemeList(data: ThemeListModel) {
            updateState(
                uiState.value.copy(
                    routineThemeList = data.themes,
                ),
            )
        }
    }
