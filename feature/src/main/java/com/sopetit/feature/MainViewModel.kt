package com.sopetit.feature

import com.sopetit.domain.entity.enums.DollType
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

): BaseViewModel<MainPageState>(MainPageState()) {

    val selectedDollType = MutableSharedFlow<DollType>(replay = 1)
}