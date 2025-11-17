package com.tdd.setting

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

fun openAppNotificationSettings(context: Context) {
    val pm = context.packageManager

    fun tryStart(intent: Intent): Boolean {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        return intent.resolveActivity(pm)?.let {
            runCatching { context.startActivity(intent) }.isSuccess
        } ?: false
    }

    tryStart(
        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", context.packageName, null)
        },
    )
}
