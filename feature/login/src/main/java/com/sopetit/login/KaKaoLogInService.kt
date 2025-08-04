package com.sopetit.login

import android.app.Activity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import javax.inject.Inject

class KaKaoLogInService @Inject constructor(
) {
    fun startKaKaoLogIn(
        activity: Activity,
        kakaoLogInCallback: (OAuthToken?, Throwable?) -> Unit,
    ) {
        val kakaoLogInState =
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(activity)) KAKAO_APP_LOGIN
            else KAKAO_ACCOUNT_LOGIN

        when (kakaoLogInState) {
            KAKAO_APP_LOGIN -> {
                UserApiClient.instance.loginWithKakaoTalk(
                    activity,
                    callback = kakaoLogInCallback
                )
            }

            KAKAO_ACCOUNT_LOGIN -> {
                UserApiClient.instance.loginWithKakaoAccount(
                    activity,
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