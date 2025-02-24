package com.sopetit.feature

import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.navigation.NavRoutes
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

) : BaseViewModel<MainPageState>(
    MainPageState()
) {

    val memberModel = MutableSharedFlow<CreateMemberModel>(replay = 1)

    fun setBottomNavType(route: String?) {
        val type = when (route) {
            NavRoutes.HomeScreen.route -> {
                BottomNavType.HOME
            }

            NavRoutes.ProgressScreen.route -> {
                BottomNavType.PROGRESS
            }

            NavRoutes.AchieveScreen.route -> {
                BottomNavType.ACHIEVE
            }

            else -> {
                BottomNavType.DEFAULT
            }
        }

        updateBottomNav(type)
    }

    private fun updateBottomNav(type: BottomNavType) {
        updateState(
            uiState.value.copy(
                bottomNavType = type
            )
        )
    }
}