package com.sopetit.softie.di

import com.sopetit.data.service.AchievementService
import com.sopetit.data.service.AuthService
import com.sopetit.data.service.CalendarService
import com.sopetit.data.service.CustomRoutineService
import com.sopetit.data.service.MemberChallengeService
import com.sopetit.data.service.MemberRoutineService
import com.sopetit.data.service.MemberService
import com.sopetit.data.service.MemoService
import com.sopetit.data.service.RefreshTokenService
import com.sopetit.data.service.RoutineService
import com.sopetit.data.service.ThemeService
import com.sopetit.data.service.VersionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideAuthService(@SoftieRetrofit retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    fun provideVersionService(@SoftieRetrofit retrofit: Retrofit): VersionService {
        return retrofit.create(VersionService::class.java)
    }

    @Provides
    fun provideRefreshService(@RefreshTokenRetrofit retrofit: Retrofit): RefreshTokenService {
        return retrofit.create(RefreshTokenService::class.java)
    }

    @Provides
    fun provideThemeService(@SoftieRetrofit retrofit: Retrofit): ThemeService {
        return retrofit.create(ThemeService::class.java)
    }

    @Provides
    fun provideRoutineService(@SoftieRetrofit retrofit: Retrofit): RoutineService {
        return retrofit.create(RoutineService::class.java)
    }

    @Provides
    fun provideMemberService(@SoftieRetrofit retrofit: Retrofit): MemberService {
        return retrofit.create(MemberService::class.java)
    }

    @Provides
    fun provideMemberRoutineService(@SoftieRetrofit retrofit: Retrofit): MemberRoutineService {
        return retrofit.create(MemberRoutineService::class.java)
    }

    @Provides
    fun provideMemberChallengeService(@SoftieRetrofit retrofit: Retrofit): MemberChallengeService {
        return retrofit.create(MemberChallengeService::class.java)
    }

    @Provides
    fun provideCalendarService(@SoftieRetrofit retrofit: Retrofit): CalendarService {
        return retrofit.create(CalendarService::class.java)
    }

    @Provides
    fun provideMemoService(@SoftieRetrofit retrofit: Retrofit): MemoService {
        return retrofit.create(MemoService::class.java)
    }

    @Provides
    fun provideAchieveService(@SoftieRetrofit retrofit: Retrofit): AchievementService {
        return retrofit.create(AchievementService::class.java)
    }

    @Provides
    fun provideCustomRoutineService(@SoftieRetrofit retrofit: Retrofit): CustomRoutineService {
        return retrofit.create(CustomRoutineService::class.java)
    }
}