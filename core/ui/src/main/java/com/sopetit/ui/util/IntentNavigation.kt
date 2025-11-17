package com.sopetit.ui.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.sopetit.ui.common.type.IntentNavigationType

fun intentToUrl(
    context: Context,
    type: IntentNavigationType,
) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(type.url))
    context.startActivity(intent)
}
