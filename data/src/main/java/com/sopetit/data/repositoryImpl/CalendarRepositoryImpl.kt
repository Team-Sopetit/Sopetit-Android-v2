package com.sopetit.data.repositoryImpl

import com.sopetit.data.dataSource.CalendarDataSource
import com.sopetit.data.mapper.calendar.GetCalendarMapper
import com.sopetit.domain.entity.request.calendar.CalendarRequestModel
import com.sopetit.domain.entity.response.calendar.CalendarModel
import com.sopetit.domain.repository.CalendarRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CalendarRepositoryImpl
    @Inject
    constructor(
        private val calendarDataSource: CalendarDataSource,
    ) : CalendarRepository {
        override suspend fun getCalendar(request: CalendarRequestModel): Flow<Result<Map<String, CalendarModel>>> =
            GetCalendarMapper.responseToModel(apiCall = {
                calendarDataSource.calendarList(
                    request.year,
                    request.month,
                )
            })
    }
