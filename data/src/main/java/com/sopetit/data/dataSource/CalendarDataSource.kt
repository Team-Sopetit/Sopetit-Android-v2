package com.sopetit.data.dataSource

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.response.calendar.GetCalendarResponseDto
import retrofit2.Response

interface CalendarDataSource {
    suspend fun calendarList(year: Int, month: Int): Response<BaseResponse<List<Map<String, GetCalendarResponseDto>>>>
}