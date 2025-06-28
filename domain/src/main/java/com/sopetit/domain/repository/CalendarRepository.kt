package com.sopetit.domain.repository

import com.sopetit.domain.entity.request.calendar.CalendarRequestModel
import com.sopetit.domain.entity.response.calendar.CalendarModel
import kotlinx.coroutines.flow.Flow

interface CalendarRepository {
    suspend fun getCalendar(request: CalendarRequestModel): Flow<Result<List<Map<String, CalendarModel>>>>
}