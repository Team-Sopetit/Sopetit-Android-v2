package com.sopetit.domain.usecase.calendar

import com.sopetit.domain.base.UseCase
import com.sopetit.domain.entity.request.calendar.CalendarRequestModel
import com.sopetit.domain.entity.response.calendar.CalendarModel
import com.sopetit.domain.repository.CalendarRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCalendarUseCase @Inject constructor(
    private val calendarRepository: CalendarRepository,
) : UseCase<CalendarRequestModel, Result<Map<String, CalendarModel>>>() {

    override suspend fun invoke(request: CalendarRequestModel): Flow<Result<Map<String, CalendarModel>>> =
        calendarRepository.getCalendar(request)
}