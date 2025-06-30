package com.sopetit.softie.di

import com.sopetit.data.repositoryImpl.AuthRepositoryImpl
import com.sopetit.data.repositoryImpl.CalendarRepositoryImpl
import com.sopetit.data.repositoryImpl.MemberChallengeRepositoryImpl
import com.sopetit.data.repositoryImpl.MemberRepositoryImpl
import com.sopetit.data.repositoryImpl.MemberRoutineRepositoryImpl
import com.sopetit.data.repositoryImpl.MemoRepositoryImpl
import com.sopetit.data.repositoryImpl.RoutineRepositoryImpl
import com.sopetit.data.repositoryImpl.ThemeRepositoryImpl
import com.sopetit.domain.repository.AuthRepository
import com.sopetit.domain.repository.CalendarRepository
import com.sopetit.domain.repository.MemberChallengeRepository
import com.sopetit.domain.repository.MemberRepository
import com.sopetit.domain.repository.MemberRoutineRepository
import com.sopetit.domain.repository.MemoRepository
import com.sopetit.domain.repository.RoutineRepository
import com.sopetit.domain.repository.ThemeRepository
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

    @Binds
    @Singleton
    abstract fun bindsThemeRepository(repositoryImpl: ThemeRepositoryImpl): ThemeRepository

    @Binds
    @Singleton
    abstract fun bindsRoutineRepository(repositoryImpl: RoutineRepositoryImpl): RoutineRepository

    @Binds
    @Singleton
    abstract fun bindsMemberRepository(repositoryImpl: MemberRepositoryImpl): MemberRepository

    @Binds
    @Singleton
    abstract fun bindsMemberRoutineRepository(repositoryImpl: MemberRoutineRepositoryImpl): MemberRoutineRepository

    @Binds
    @Singleton
    abstract fun bindsMemberChallengeRepository(repositoryImpl: MemberChallengeRepositoryImpl): MemberChallengeRepository

    @Binds
    @Singleton
    abstract fun bindsCalendarRepository(repositoryImpl: CalendarRepositoryImpl): CalendarRepository

    @Binds
    @Singleton
    abstract fun bindsMemoRepository(repositoryImpl: MemoRepositoryImpl): MemoRepository
}