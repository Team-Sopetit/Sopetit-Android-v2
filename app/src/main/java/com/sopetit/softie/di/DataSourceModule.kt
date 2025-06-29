package com.sopetit.softie.di

import com.sopetit.data.dataSource.AuthDataSource
import com.sopetit.data.dataSource.CalendarDataSource
import com.sopetit.data.dataSource.MemberChallengeDataSource
import com.sopetit.data.dataSource.MemberDataSource
import com.sopetit.data.dataSource.MemberRoutineDataSource
import com.sopetit.data.dataSource.RoutineDataSource
import com.sopetit.data.dataSource.ThemeDataSource
import com.sopetit.data.dataSource.dataSourceImpl.AuthDataSourceImpl
import com.sopetit.data.dataSource.dataSourceImpl.CalendarDataSourceImpl
import com.sopetit.data.dataSource.dataSourceImpl.MemberChallengeDataSourceImpl
import com.sopetit.data.dataSource.dataSourceImpl.MemberDataSourceImpl
import com.sopetit.data.dataSource.dataSourceImpl.MemberRoutineDataSourceImpl
import com.sopetit.data.dataSource.dataSourceImpl.RoutineDataSourceImpl
import com.sopetit.data.dataSource.dataSourceImpl.ThemeDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource

    @Binds
    @Singleton
    abstract fun bindsThemeDataSource(themeDataSourceImpl: ThemeDataSourceImpl): ThemeDataSource

    @Binds
    @Singleton
    abstract fun bindsRoutineDataSource(routineDataSourceImpl: RoutineDataSourceImpl): RoutineDataSource

    @Binds
    @Singleton
    abstract fun bindsMemberDataSource(memberDataSourceImpl: MemberDataSourceImpl): MemberDataSource

    @Binds
    @Singleton
    abstract fun bindsMemberRoutineDataSource(memberRoutineDataSourceImpl: MemberRoutineDataSourceImpl): MemberRoutineDataSource

    @Binds
    @Singleton
    abstract fun bindsMemberChallengeDataSource(memberChallengeDataSourceImpl: MemberChallengeDataSourceImpl): MemberChallengeDataSource

    @Binds
    @Singleton
    abstract fun bindsCalendarDataSource(calendarDataSourceImpl: CalendarDataSourceImpl): CalendarDataSource
}