package com.sopetit.ui.common.type

import com.sopetit.ui.BuildConfig

enum class IntentNavigationType(
    val url: String,
) {
    FEEDBACK(BuildConfig.FEEDBACK_FORM),
    SERVICEPOLICY(BuildConfig.SERVICE_POLICY),
    PERSONALINFOPOLICY(BuildConfig.PERSONAL_INFO_POLICY),
}
