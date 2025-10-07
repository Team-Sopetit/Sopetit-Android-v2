package com.tdd.setting.deleteuser

import androidx.lifecycle.viewModelScope
import com.sopetit.domain.entity.response.member.GetMemberModel
import com.sopetit.domain.usecase.auth.DeleteUserUseCase
import com.sopetit.domain.usecase.member.GetMemberUseCase
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteUserViewModel @Inject constructor(
    private val deleteUserUseCase: DeleteUserUseCase,
    private val getMemberUseCase: GetMemberUseCase,
) : BaseViewModel<DeleteUserPageState>(
    DeleteUserPageState()
) {

    init {
        initSetDollType()
    }

    private fun initSetDollType() {
        viewModelScope.launch {
            getMemberUseCase(Unit).collect { resultResponse(it, ::onSuccessGetMember) }
        }
    }

    private fun onSuccessGetMember(data: GetMemberModel) {
        updateState(
            uiState.value.copy(
                dollType = data.dollType
            )
        )
    }

    fun deleteUser() {
        viewModelScope.launch {
            deleteUserUseCase(Unit).collect { resultResponse(it, {}) }
        }
    }
}