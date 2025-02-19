package com.sopetit.softie.di

import com.sopetit.data.dataSource.AuthDataSource
import com.sopetit.data.dataSource.RoutineDataSource
import com.sopetit.data.dataSource.ThemeDataSource
import com.sopetit.data.dataSource.dataSourceImpl.AuthDataSourceImpl
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
}