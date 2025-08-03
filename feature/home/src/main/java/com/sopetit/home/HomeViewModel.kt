package com.sopetit.home

import androidx.lifecycle.viewModelScope
import com.sopetit.design_system.R
import com.sopetit.domain.entity.response.member.GetMemberModel
import com.sopetit.domain.entity.response.screen.TutorialModel
import com.sopetit.domain.usecase.member.GetMemberUseCase
import com.sopetit.domain.usecase.member.PatchCottonUseCase
import com.sopetit.ui.base.BaseViewModel
import com.sopetit.ui.common.type.BearType
import com.sopetit.ui.common.type.CottonType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMemberUseCase: GetMemberUseCase,
    private val patchCottonUseCase: PatchCottonUseCase
) : BaseViewModel<HomePageState>(
    HomePageState()
) {

    init {
        initSetTutorial()
        initGetHomeMember()
    }

    private fun initSetTutorial() {
        val tutorials: List<TutorialModel> = listOf(
            TutorialModel(
                1,
                "데일리 루틴",
                "매일 작은 성취감을 만드는 루틴",
                "매일 실천할 수 있는 쉬운 루틴을 제안해요.\n루틴을 완료하면 솜뭉치를 얻어요.",
                R.drawable.ic_tutorial_page1
            ),
            TutorialModel(
                2,
                "챌린지",
                "나를 찾아가는 특별한 루틴",
                "특별한 일상을 위한 챌린지형 루틴이에요.\n루틴을 완료하면 무지개 솜뭉치를 얻어요.",
                R.drawable.ic_tutorial_page2
            ),
            TutorialModel(
                3,
                "솜뭉치 주기",
                "솜뭉치를 얻으면?",
                "얻은 솜뭉치는 곰인형에게 줄 수 있어요!\n곰인형이 어떤 반응을 보일까요?",
                R.drawable.ic_tutorial_page3
            )
        )

        updateState(
            uiState.value.copy(
                tutorialList = tutorials
            )
        )
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
                randomSelectedConversation = data.conversations[0],
                dailyCottonCount = data.dailyCottonCount,
                happinessCottonCount = data.happinessCottonCount
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

    fun updateTutorialValid(isValid: Boolean) {
        updateState(
            uiState.value.copy(
                isTutorialValid = isValid
            )
        )
    }

    fun patchCotton(cottonType: CottonType) {
        viewModelScope.launch {
            patchCottonUseCase(cottonType.toString()).collect {
                resultResponse(it, { data -> onSuccessPatchCotton(data, cottonType)})
            }
        }
    }

    private fun onSuccessPatchCotton(data: Int, type: CottonType) {
        when (type) {
            CottonType.DAILY -> updateDailyCottonCount(data)
            CottonType.HAPPINESS -> updateHappinessCottonCount(data)
        }
    }

    private fun updateDailyCottonCount(data: Int) {
        updateState(
            uiState.value.copy(
                dailyCottonCount = data
            )
        )
    }

    private fun updateHappinessCottonCount(data: Int) {
        updateState(
            uiState.value.copy(
                happinessCottonCount = data
            )
        )
    }
}