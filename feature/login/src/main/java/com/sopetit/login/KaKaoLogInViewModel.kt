package com.sopetit.login

import com.kakao.sdk.auth.model.OAuthToken
import com.sopetit.ui.base.BaseViewModel
import com.sopetit.ui.base.PageState
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class KaKaoLogInViewModel @Inject constructor(
    private val kakaoLoginService: KaKaoLogInService
): BaseViewModel<PageState.Default>(
    PageState.Default
) {

    private val kakaoLogInCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        Timber.d("[로그인] 테스트 -> token: $token, error: $error")
        KaKaoLogInCallback { accessToken ->
            //
        }.handleResult(token, error)
    }

    fun startKaKaoLogIn() {
        kakaoLoginService.startKaKaoLogIn(kakaoLogInCallback)
    }
}