package com.sopetit.data.dataSource

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.response.calendar.GetCalendarItemResponseDto
import retrofit2.Response

interface CalendarDataSource {
    suspend fun calendarList(year: Int, month: Int): Response<BaseResponse<Map<String, GetCalendarItemResponseDto>>>
}