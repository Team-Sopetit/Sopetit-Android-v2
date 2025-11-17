package com.sopetit.achieve.stats

import com.sopetit.domain.entity.response.achieve.AchieveModel
import com.sopetit.ui.base.PageState

data class StatPageState(
    val achieveModel: AchieveModel = AchieveModel(),
) : PageState
