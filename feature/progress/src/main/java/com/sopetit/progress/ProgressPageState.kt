package com.sopetit.progress

import com.sopetit.domain.entity.response.memberroutine.MemberDailyRoutineListModel
import com.sopetit.ui.base.PageState

data class ProgressPageState(
    val memberDailyRoutineList: List<MemberDailyRoutineListModel> = emptyList(),
) : PageState