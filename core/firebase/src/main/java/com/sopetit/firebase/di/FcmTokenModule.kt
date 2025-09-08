package com.sopetit.firebase.di

import com.sopetit.firebase.fcmtoken.FcmTokenProvider
import com.sopetit.firebase.fcmtoken.FcmTokenProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FcmTokenModule {
    @Provides
    @Singleton
    fun provideFcmTokenProviderImpl(): FcmTokenProvider = FcmTokenProviderImpl()
}