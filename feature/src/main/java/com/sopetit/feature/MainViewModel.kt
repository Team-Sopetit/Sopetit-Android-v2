package com.sopetit.feature

import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.ui.base.BaseViewModel
import com.sopetit.ui.base.PageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

) : BaseViewModel<PageState.Default>(PageState.Default) {

    val memberModel = MutableSharedFlow<CreateMemberModel>(replay = 1)

}