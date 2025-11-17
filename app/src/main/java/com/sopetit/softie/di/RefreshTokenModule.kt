package com.sopetit.softie.di

import com.sopetit.data.dataStore.LocalDataStore
import com.sopetit.softie.BuildConfig
import com.sopetit.ui.util.CommonEventManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RefreshTokenModule {
    private val json = Json { ignoreUnknownKeys = true }
    private const val CONTENT_TYPE = "Content-Type"
    private const val APPLICATION_JSON = "application/json"
    private const val BEARER = "Bearer "
    private const val AUTHORIZATION = "Authorization"
    private const val EXPIRED_TOKEN = 401
    private const val BAD_REQUEST = 400

    @Singleton
    @Provides
    @RefreshTokenRetrofit
    fun providesAuthInterceptor(
        localDataSource: LocalDataStore,
    ): Interceptor =
        Interceptor { chain ->
            val tokenFlow = localDataSource.refreshToken
            val token = runBlocking { tokenFlow.first() }

            val request = chain.request()
            val response =
                chain.proceed(
                    request
                        .newBuilder()
                        .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                        .addHeader(AUTHORIZATION, BEARER + token)
                        .build(),
                )
            when (response.code) {
                EXPIRED_TOKEN, BAD_REQUEST -> {
                    runBlocking {
                        CommonEventManager.triggerLogout()
                    }
                }
            }
            return@Interceptor response
        }

    @Singleton
    @Provides
    @RefreshTokenRetrofit
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @RefreshTokenRetrofit interceptor: Interceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(interceptor)
            .build()

    @Provides
    @Singleton
    @RefreshTokenRetrofit
    fun providesRetrofit(
        @RefreshTokenRetrofit okHttpClient: OkHttpClient,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(APPLICATION_JSON.toMediaType()))
            .build()
}
