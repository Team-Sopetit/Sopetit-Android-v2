package com.sopetit.softie.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SoftieRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RefreshTokenRetrofit
