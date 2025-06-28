package com.sopetit.data.mapper.calendar

import com.sopetit.data.base.BaseMapper
import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.response.calendar.GetCalendarResponseDto
import com.sopetit.domain.entity.response.calendar.CalendarHistoryItemModel
import com.sopetit.domain.entity.response.calendar.CalendarHistoryModel
import com.sopetit.domain.entity.response.calendar.CalendarModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object GetCalendarMapper : BaseMapper() {

    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<List<Map<String, GetCalendarResponseDto>>>>): Flow<Result<List<Map<String, CalendarModel>>>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.map { dateMap ->
                    dateMap.mapValues { (_, dto) ->
                        CalendarModel(
                            memoId = dto.memoId,
                            memoContent = dto.memoContent,
                            histories = dto.histories.map { history ->
                                CalendarHistoryModel(
                                    themeId = history.themeId,
                                    themeName = history.themeName,
                                    histories = history.histories.map { historyItem ->
                                        CalendarHistoryItemModel(
                                            historyId = historyItem.historyId,
                                            content = historyItem.content,
                                            isChallenge = historyItem.isChallenge
                                        )
                                    }
                                )
                            }
                        )
                    }
                } ?: emptyList()
            }
        )
    }
}