package com.sopetit.home

import com.sopetit.domain.entity.response.member.GetMemberModel
import com.sopetit.ui.base.PageState

data class HomePageState(
    val homeMemberModel: GetMemberModel = GetMemberModel(),
) : PageState