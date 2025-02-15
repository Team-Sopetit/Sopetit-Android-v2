package com.sopetit.login

import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.sopetit.domain.entity.request.LogInRequestModel
import com.sopetit.domain.entity.response.TokenStoreModel
import com.sopetit.domain.usecase.PostLogInUseCase
import com.sopetit.domain.usecase.SaveTokenUseCase
import com.sopetit.ui.base.BaseViewModel
import com.sopetit.ui.base.PageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class KaKaoLogInViewModel @Inject constructor(
    private val kakaoLoginService: KaKaoLogInService,
    private val postLogInUseCase: PostLogInUseCase,
    private val saveTokenUseCase: SaveTokenUseCase
): BaseViewModel<KaKaoLogInPageState>(
    KaKaoLogInPageState()
) {

    private val _isKaKaoLogInSuccess = MutableStateFlow(false)
    val isKaKaoLogInSuccess: StateFlow<Boolean>
        get() = _isKaKaoLogInSuccess

    private fun updateKaKaoLogInSuccess() {
        _isKaKaoLogInSuccess.value = true
    }

    private val kakaoLogInCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        KaKaoLogInCallback { accessToken ->
            viewModelScope.launch {
                saveTokenUseCase(
                    request = TokenStoreModel(
                        accessToken = accessToken,
                        refreshToken = ""
                    )
                ).collect{
                    Timber.d("[로그인] saveTokenUseCase 결과: $it")
                    resultResponse(it, { postLogIn()
                        Timber.d("[로그인] 테스트 (후)")}, { error ->
                        Timber.d("[로그인] 토큰 저장 실패 -> ${error.message}")
                    })
                }
            }
//            postLogIn()
        }.handleResult(token, error)
        Timber.d("[로그인] 테스트 (scope 후)")
        updateKaKaoLogInSuccess()
//        postLogIn()
    }

    fun startKaKaoLogIn() {
        kakaoLoginService.startKaKaoLogIn(kakaoLogInCallback)
    }

    fun postLogIn() {
        viewModelScope.launch {
            postLogInUseCase(
                request = LogInRequestModel(SOCIAL_TYPE)
            ).collect {
                resultResponse(it, {
                    emitEventFlow(KaKaoLogInEvent.OnSuccessLogIn)
                    Timber.d("[로그인] 서버 통신 성공")
                }, { error ->
                    Timber.d("[로그인] 서버 통신 실패 -> ${error.message}")
                })
            }
        }
//        emitEventFlow(KaKaoLogInEvent.OnSuccessLogIn)
    }

    companion object {
        private const val SOCIAL_TYPE = "KAKAO"
    }
}