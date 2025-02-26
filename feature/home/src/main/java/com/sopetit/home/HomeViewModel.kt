package com.sopetit.home

import androidx.lifecycle.viewModelScope
import com.sopetit.domain.entity.response.member.GetMemberModel
import com.sopetit.domain.usecase.member.GetMemberUseCase
import com.sopetit.ui.base.BaseViewModel
import com.sopetit.ui.common.type.BearType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMemberUseCase: GetMemberUseCase,
) : BaseViewModel<HomePageState>(
    HomePageState()
) {

    init {
        initGetHomeMember()
    }

    private fun initGetHomeMember() {
        viewModelScope.launch {
            getMemberUseCase(request = Unit).collect {
                resultResponse(it, ::onSuccessGetMember)
            }
        }
    }

    private fun onSuccessGetMember(data: GetMemberModel) {
        updateState(
            uiState.value.copy(
                homeMemberModel = data,
                dollHelloResource = BearType.getDollHelloResource(data.dollType),
                randomSelectedConversation = data.conversations[0]
            )
        )
    }

    fun updateRandomConversation() {
        val randomSplashIndex: Int =
            Random.nextInt(uiState.value.homeMemberModel.conversations.size)

        updateState(
            uiState.value.copy(
                randomSelectedConversation = uiState.value.homeMemberModel.conversations[randomSplashIndex]

            )
        )
    }
}