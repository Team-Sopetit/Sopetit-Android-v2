package com.sopetit.splash

import androidx.lifecycle.viewModelScope
import com.sopetit.domain.entity.request.LogInRequestModel
import com.sopetit.domain.entity.response.auth.AccessToken
import com.sopetit.domain.entity.response.auth.LogInResponseModel
import com.sopetit.domain.entity.response.auth.TokenStoreModel
import com.sopetit.domain.usecase.auth.GetTokenUseCase
import com.sopetit.domain.usecase.auth.PostLogInUseCase
import com.sopetit.domain.usecase.auth.RefreshTokenUseCase
import com.sopetit.domain.usecase.auth.SaveAccessTokenUseCase
import com.sopetit.domain.usecase.auth.SaveTokenUseCase
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase,
//    private val postLogInUseCase: PostLogInUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
) : BaseViewModel<SplashPageState>(SplashPageState()) {

    init {
        initCheckLocalToken()
    }

    private fun initCheckLocalToken() {
        viewModelScope.launch(Dispatchers.Main) {
            getTokenUseCase(Unit).collect {
//                if (it.accessToken.isNotEmpty() && it.refreshToken.isNotEmpty()) {
//                    reissueToken()
//                }
                resultResponse(it, ::onSuccessGetDataStore)
            }
        }
    }

    private fun onSuccessGetDataStore(data: TokenStoreModel) {
        Timber.d("[test] -> $data")
        updateState(
            uiState.value.copy(
                isMemberDollExist = data.isMemberDollExist
            )
        )

        if (data.accessToken.isNotEmpty() && data.refreshToken.isNotEmpty()) {
            reissueToken()
        }
    }

    private fun reissueToken() {
        viewModelScope.launch(Dispatchers.Main) {
            refreshTokenUseCase(Unit).collect {
                resultResponse(it, ::onSuccessReissueToken) { error ->
                    Timber.d("[로그인] 재로그인 실패 -> ${error.message}")
                    emitEventFlow(SplashEvent.GoToKaKaoLogIn)
                }
            }
        }
    }

    private fun onSuccessReissueToken(response: AccessToken) {
        viewModelScope.launch(Dispatchers.Main) {
            saveAccessTokenUseCase(response.accessToken).collect {}
//            postLogIn()
            setDestination()
        }
//        updateState(uiState.value.copy(skipLogin = true))
//        postLogIn()
    }

//    private fun postLogIn() {
//        viewModelScope.launch {
//            postLogInUseCase(LogInRequestModel(SOCIAL_TYPE)).collect { resultResponse(it, ::onSuccessLogIn) }
//        }
//    }
//
//    private fun onSuccessLogIn(data: LogInResponseModel) {
//        viewModelScope.launch {
//            saveTokenUseCase(
//                request = TokenStoreModel(
//                    accessToken = data.accessToken,
//                    refreshToken = data.refreshToken,
//                    isMemberDollExist = data.isMemberDollExist
//                )
//            ).collect { resultResponse(it, {}) }
//
//            setDestination(data.isMemberDollExist)
//        }
//    }

    private fun setDestination() {
        when (uiState.value.isMemberDollExist) {
            true -> emitEventFlow(SplashEvent.GoToHome)
            false -> emitEventFlow(SplashEvent.GoToOnboarding)
        }
    }

//    companion object {
//        private const val SOCIAL_TYPE = "KAKAO"
//    }
}