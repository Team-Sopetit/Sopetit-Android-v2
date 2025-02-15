package com.sopetit.login

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class KaKaoLogInService @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun startKaKaoLogIn(kakaoLogInCallback: (OAuthToken?, Throwable?) -> Unit) {
        val kakaoLogInState =
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) KAKAO_APP_LOGIN
            else KAKAO_ACCOUNT_LOGIN

        when (kakaoLogInState) {
            KAKAO_APP_LOGIN -> {
                UserApiClient.instance.loginWithKakaoTalk(
                    context,
                    callback = kakaoLogInCallback
                )
            }

            KAKAO_ACCOUNT_LOGIN -> {
                UserApiClient.instance.loginWithKakaoAccount(
                    context,
                    callback = kakaoLogInCallback
                )
            }
        }
    }

    companion object {
        const val KAKAO_APP_LOGIN = 0
        const val KAKAO_ACCOUNT_LOGIN = 1
    }
}