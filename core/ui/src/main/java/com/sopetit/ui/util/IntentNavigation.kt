package com.sopetit.ui.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.sopetit.ui.BuildConfig

fun intentToFeedback(context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.FEEDBACK_FORM))
    context.startActivity(intent)
}