package com.sopetit.softie.di

import com.sopetit.data.repositoryImpl.AuthRepositoryImpl
import com.sopetit.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsAuthRepository(repositoryImpl: AuthRepositoryImpl): AuthRepository
}