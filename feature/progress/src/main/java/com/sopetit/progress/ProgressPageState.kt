package com.sopetit.progress

import com.sopetit.domain.entity.response.memberchallenge.MemberChallengeModel
import com.sopetit.domain.entity.response.memberroutine.MemberDailyRoutineListModel
import com.sopetit.ui.base.PageState

data class ProgressPageState(
    val memberDailyRoutineList: List<MemberDailyRoutineListModel> = emptyList(),
    val memberChallenge: MemberChallengeModel = MemberChallengeModel(),
) : PageState