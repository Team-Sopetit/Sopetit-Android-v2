package com.tdd.setting.deleteuser

import androidx.lifecycle.viewModelScope
import com.sopetit.domain.usecase.auth.DeleteUserUseCase
import com.sopetit.ui.base.BaseViewModel
import com.sopetit.ui.base.PageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteUserViewModel @Inject constructor(
    private val deleteUserUseCase: DeleteUserUseCase
): BaseViewModel<PageState.Default>(PageState.Default) {

    fun deleteUser() {
        viewModelScope.launch {
            deleteUserUseCase(Unit).collect { resultResponse(it, {} )}
        }
    }
}