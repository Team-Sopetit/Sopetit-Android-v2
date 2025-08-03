package com.sopetit.login

import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.sopetit.domain.entity.request.LogInRequestModel
import com.sopetit.domain.entity.response.auth.LogInResponseModel
import com.sopetit.domain.entity.response.auth.TokenStoreModel
import com.sopetit.domain.usecase.auth.PostLogInUseCase
import com.sopetit.domain.usecase.auth.SaveTokenUseCase
import com.sopetit.domain.usecase.member.PostFcmTokenUseCase
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class KaKaoLogInViewModel @Inject constructor(
    private val kakaoLoginService: KaKaoLogInService,
    private val postLogInUseCase: PostLogInUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val postFcmTokenUseCase: PostFcmTokenUseCase
) : BaseViewModel<KaKaoLogInPageState>(
    KaKaoLogInPageState()
) {

    private val kakaoLogInCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        KaKaoLogInCallback { accessToken ->
            viewModelScope.launch {
                saveTokenUseCase(
                    request = TokenStoreModel(
                        accessToken = accessToken,
                        refreshToken = "",
                        isMemberDollExist = false
                    )
                ).collect { resultResponse(it, {}) }

                postLogIn()
            }
        }.handleResult(token, error)
    }

    fun startKaKaoLogIn() {
        kakaoLoginService.startKaKaoLogIn(kakaoLogInCallback)
    }

    private fun postLogIn() {
        viewModelScope.launch {
            postLogInUseCase(
                request = LogInRequestModel(SOCIAL_TYPE)
            ).collect {
                resultResponse(it, ::onSuccessKaKaoLogIn) { error ->
                    Timber.d("[로그인] 서버 통신 실패 -> ${error.message}")
                }
            }
        }
    }

    private fun onSuccessKaKaoLogIn(data: LogInResponseModel) {
        viewModelScope.launch {
            saveTokenUseCase(
                request = TokenStoreModel(
                    accessToken = data.accessToken,
                    refreshToken = data.refreshToken,
                    isMemberDollExist = data.isMemberDollExist
                )
            ).collect { resultResponse(it, {}) }

            postFcmToken(data.isMemberDollExist)
//            setDestination(data.isMemberDollExist)
        }
    }

    private fun postFcmToken(data: Boolean) {
        viewModelScope.launch {
            postFcmTokenUseCase(Unit).collect{ resultResponse(it, {} )}

            setDestination(data)
        }
    }

    private fun setDestination(isMemberDollExist: Boolean) {
        when (isMemberDollExist) {
            true -> emitEventFlow(KaKaoLogInEvent.GoToHomePage)
            false -> emitEventFlow(KaKaoLogInEvent.GoToOnBoardingPage)
        }
    }

    companion object {
        private const val SOCIAL_TYPE = "KAKAO"
    }
}