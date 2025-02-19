package com.sopetit.feature

import androidx.lifecycle.viewModelScope
import com.sopetit.domain.entity.enums.DollType
import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

): BaseViewModel<MainPageState>(MainPageState()) {

//    val selectedDollType = MutableSharedFlow<DollType>(replay = 1)
    val memberModel = MutableSharedFlow<CreateMemberModel>(replay = 1)
    val selectedThemeIds = MutableSharedFlow<List<Int>>(replay = 1)

//    private val _selectedDollType = MutableSharedFlow<DollType>(replay = 1)
//    val selectedDollType: SharedFlow<DollType> = _selectedDollType
//
//    fun setSelectedDollType(dollType: DollType) {
//        viewModelScope.launch {
//            _selectedDollType.emit(dollType)
//        }
//    }


//    fun setSelectedDollType(dollType: DollType) {
//        updateState(
//            uiState.value.copy(
//                selectedDollType = dollType
//            )
//        )
//
//        Timber.d("[메인] dollType -> $dollType && ${uiState.value.selectedDollType}")
//    }
}