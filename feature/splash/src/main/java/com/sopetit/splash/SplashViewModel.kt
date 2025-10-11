package com.sopetit.splash

import androidx.lifecycle.viewModelScope
import com.sopetit.domain.entity.response.auth.AccessToken
import com.sopetit.domain.entity.response.auth.TokenStoreModel
import com.sopetit.domain.usecase.auth.GetTokenUseCase
import com.sopetit.domain.usecase.auth.RefreshTokenUseCase
import com.sopetit.domain.usecase.auth.SaveAccessTokenUseCase
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
) : BaseViewModel<SplashPageState>(SplashPageState()) {

    init {
        initCheckLocalToken()
    }

    private fun initCheckLocalToken() {
        viewModelScope.launch(Dispatchers.Main) {
            getTokenUseCase(Unit).collect { resultResponse(it, ::onSuccessGetDataStore) { error ->
                Timber.d("[datastore] getToken 실패 $error")
                emitEventFlow(SplashEvent.GoToKaKaoLogIn)
            } }
        }
    }

    private fun onSuccessGetDataStore(data: TokenStoreModel) {
        updateState(
            uiState.value.copy(
                isMemberDollExist = data.isMemberDollExist
            )
        )

        if (data.accessToken.isNotEmpty() && data.refreshToken.isNotEmpty()) {
            reissueToken()
        } else {
            emitEventFlow(SplashEvent.GoToKaKaoLogIn)
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
            setDestination()
        }
    }

    private fun setDestination() {
        when (uiState.value.isMemberDollExist) {
            true -> emitEventFlow(SplashEvent.GoToHome)
            false -> emitEventFlow(SplashEvent.GoToOnboarding)
        }
    }
}