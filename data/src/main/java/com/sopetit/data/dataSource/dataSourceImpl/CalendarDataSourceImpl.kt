package com.sopetit.data.dataSource.dataSourceImpl

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.dataSource.CalendarDataSource
import com.sopetit.data.entity.response.calendar.GetCalendarItemResponseDto
import com.sopetit.data.service.CalendarService
import retrofit2.Response
import javax.inject.Inject

class CalendarDataSourceImpl
    @Inject
    constructor(
        private val calendarService: CalendarService,
    ) : CalendarDataSource {
        override suspend fun calendarList(
            year: Int,
            month: Int,
        ): Response<BaseResponse<Map<String, GetCalendarItemResponseDto>>> =
            calendarService.getCalendar(year, month)
    }
