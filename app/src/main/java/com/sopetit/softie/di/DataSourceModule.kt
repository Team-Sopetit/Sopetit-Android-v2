package com.sopetit.softie.di

import com.sopetit.data.dataSource.AchieveDataSource
import com.sopetit.data.dataSource.AuthDataSource
import com.sopetit.data.dataSource.CalendarDataSource
import com.sopetit.data.dataSource.CustomRoutineDataSource
import com.sopetit.data.dataSource.MemberChallengeDataSource
import com.sopetit.data.dataSource.MemberDataSource
import com.sopetit.data.dataSource.MemberRoutineDataSource
import com.sopetit.data.dataSource.MemoDataSource
import com.sopetit.data.dataSource.RefreshDataSource
import com.sopetit.data.dataSource.RoutineDataSource
import com.sopetit.data.dataSource.ThemeDataSource
import com.sopetit.data.dataSource.VersionDataSource
import com.sopetit.data.dataSource.dataSourceImpl.AchieveDataSourceImpl
import com.sopetit.data.dataSource.dataSourceImpl.AuthDataSourceImpl
import com.sopetit.data.dataSource.dataSourceImpl.CalendarDataSourceImpl
import com.sopetit.data.dataSource.dataSourceImpl.CustomRoutineDataSourceImpl
import com.sopetit.data.dataSource.dataSourceImpl.MemberChallengeDataSourceImpl
import com.sopetit.data.dataSource.dataSourceImpl.MemberDataSourceImpl
import com.sopetit.data.dataSource.dataSourceImpl.MemberRoutineDataSourceImpl
import com.sopetit.data.dataSource.dataSourceImpl.MemoDataSourceImpl
import com.sopetit.data.dataSource.dataSourceImpl.RefreshDataSourceImpl
import com.sopetit.data.dataSource.dataSourceImpl.RoutineDataSourceImpl
import com.sopetit.data.dataSource.dataSourceImpl.ThemeDataSourceImpl
import com.sopetit.data.dataSource.dataSourceImpl.VersionDataSourceImpl
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
    abstract fun bindsRefreshDataSource(refreshDataSourceImpl: RefreshDataSourceImpl): RefreshDataSource

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

    @Binds
    @Singleton
    abstract fun bindsMemoDataSource(memoDataSourceImpl: MemoDataSourceImpl): MemoDataSource

    @Binds
    @Singleton
    abstract fun bindsAchieveDataSource(achieveDataSourceImpl: AchieveDataSourceImpl): AchieveDataSource

    @Binds
    @Singleton
    abstract fun bindsCustomRoutineDataSource(customRoutineDataSourceImpl: CustomRoutineDataSourceImpl): CustomRoutineDataSource

    @Binds
    @Singleton
    abstract fun bindsVersionDataSource(versionDataSourceImpl: VersionDataSourceImpl): VersionDataSource
}
