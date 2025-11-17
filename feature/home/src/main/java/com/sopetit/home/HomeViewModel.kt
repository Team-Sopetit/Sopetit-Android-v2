package com.sopetit.home

import androidx.lifecycle.viewModelScope
import com.sopetit.design_system.R
import com.sopetit.designsystem.FeedbackLeftBtn
import com.sopetit.designsystem.FeedbackRightBtn
import com.sopetit.designsystem.FeedbackSemiTitle
import com.sopetit.designsystem.FeedbackTitle
import com.sopetit.designsystem.Gray0
import com.sopetit.designsystem.Gray100
import com.sopetit.designsystem.Gray400
import com.sopetit.designsystem.PurpleGradient
import com.sopetit.domain.entity.response.member.GetMemberModel
import com.sopetit.domain.entity.response.screen.TutorialModel
import com.sopetit.domain.usecase.member.GetMemberUseCase
import com.sopetit.domain.usecase.member.PatchCottonUseCase
import com.sopetit.domain.usecase.member.PostFcmTokenUseCase
import com.sopetit.ui.base.BaseViewModel
import com.sopetit.ui.common.model.TwoBtnDialogModel
import com.sopetit.ui.common.type.BearType
import com.sopetit.ui.common.type.CottonType
import com.sopetit.ui.common.type.LottieType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMemberUseCase: GetMemberUseCase,
    private val patchCottonUseCase: PatchCottonUseCase,
    private val postFcmTokenUseCase: PostFcmTokenUseCase,
) : BaseViewModel<HomePageState>(
    HomePageState()
) {

    init {
        initSetTutorial()
        initSetFeedBackDialog()
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

    private fun initSetFeedBackDialog() {
        val feedbackData = TwoBtnDialogModel(
            title = FeedbackTitle,
            semiTitle = FeedbackSemiTitle,
            leftBtnText = FeedbackLeftBtn,
            rightBtnText = FeedbackRightBtn,
            leftBtnColor = Gray100,
            leftBtnTextColor = Gray400,
            rightBtnColorBrush = PurpleGradient,
            rightBtnTextColor = Gray0
        )

        updateState(
            uiState.value.copy(
                feedBackDialog = feedbackData
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
                dollLottieResource = BearType.getDollResource(data.dollType),
                dollType = data.dollType,
                dollLottieStart = LOTTIE_HELLO_START,
                dollLottieEnd = LOTTIE_DAILY_START,
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
                randomSelectedConversation = uiState.value.homeMemberModel.conversations[randomSplashIndex],
                dollCurrentMode = LottieType.HELLO

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

    fun setEatingDollType(cottonType: CottonType) {
        return when (cottonType) {
            CottonType.DAILY -> {
                updateState(
                    uiState.value.copy(
                        dollCurrentMode = LottieType.EATING,
                        dollLottieStart = LOTTIE_DAILY_START,
                        dollLottieEnd = LOTTIE_CHALLENGE_START
                    )
                )
            }

            CottonType.HAPPINESS -> {
                updateState(
                    uiState.value.copy(
                        dollCurrentMode = LottieType.EATING,
                        dollLottieStart = LOTTIE_CHALLENGE_START,
                        dollLottieEnd = LOTTIE_END
                    )
                )
            }
        }
    }

    fun setCurrentDollHello(lottieType: LottieType) {
        updateState(
            uiState.value.copy(
                dollCurrentMode = lottieType,
                dollLottieStart = LOTTIE_HELLO_START,
                dollLottieEnd = LOTTIE_DAILY_START
            )
        )
    }

    fun patchCotton(cottonType: CottonType) {
        viewModelScope.launch {
            patchCottonUseCase(cottonType.toString()).collect {
                resultResponse(it, { data -> onSuccessPatchCotton(data, cottonType) })
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

    fun postFCMToken() {
        viewModelScope.launch {
            postFcmTokenUseCase(Unit).collect { resultResponse(it, {}) }
        }
    }

    companion object {
        const val LOTTIE_HELLO_START = 0f
        const val LOTTIE_DAILY_START = 0.3f
        const val LOTTIE_CHALLENGE_START = 0.65f
        const val LOTTIE_END = 1f
    }
}