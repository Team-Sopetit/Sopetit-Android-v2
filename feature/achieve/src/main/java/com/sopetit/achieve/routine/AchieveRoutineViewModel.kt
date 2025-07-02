package com.sopetit.achieve.routine

import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AchieveRoutineViewModel @Inject constructor(

): BaseViewModel<AchieveRoutinePageState>(
    AchieveRoutinePageState()
) {

    fun getAchieveThemeRoutine(themeId: Int) {
        Timber.d("[테스트] -> $themeId")
    }
}