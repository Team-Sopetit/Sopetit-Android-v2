package com.sopetit.data.service

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.base.EndPoints
import com.sopetit.data.entity.response.calendar.GetCalendarResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CalendarService {

    @GET(EndPoints.Calendar.CALENDAR)
    suspend fun getCalendar(
        @Query("year") year: Int,
        @Query("month") month: Int
    ): Response<BaseResponse<List<Map<String, GetCalendarResponseDto>>>>
}