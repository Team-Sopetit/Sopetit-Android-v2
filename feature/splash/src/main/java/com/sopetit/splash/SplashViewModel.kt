package com.sopetit.splash

import androidx.lifecycle.viewModelScope
import com.sopetit.domain.entity.response.auth.AccessToken
import com.sopetit.domain.usecase.auth.GetTokenUseCase
import com.sopetit.domain.usecase.auth.RefreshTokenUseCase
import com.sopetit.domain.usecase.auth.SaveAccessTokenUseCase
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
            getTokenUseCase(Unit).collect {
                if (it.accessToken.isNotEmpty() && it.refreshToken.isNotEmpty()) {
                    reissueToken()
                }
            }
        }
    }

    private fun reissueToken() {
        viewModelScope.launch(Dispatchers.Main) {
            refreshTokenUseCase(Unit).collect {
                resultResponse(it, ::onSuccessReissueToken)
            }
        }
    }

    private fun onSuccessReissueToken(response: AccessToken) {
        viewModelScope.launch(Dispatchers.Main) {
            saveAccessTokenUseCase(response.accessToken).collect {}
        }
        updateState(uiState.value.copy(skipLogin = true))
    }
}