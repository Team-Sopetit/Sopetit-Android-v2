package com.sopetit.softie.app

import dagger.hilt.android.HiltAndroidApp
import android.app.Application
import timber.log.Timber

@HiltAndroidApp
class Application: Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}