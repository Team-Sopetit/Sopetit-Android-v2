package com.sopetit.data.mapper.memo

import com.sopetit.data.base.BaseMapper
import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.request.memo.MemoWriteRequestDto
import com.sopetit.data.entity.response.memo.MemoWriteResponseDto
import com.sopetit.domain.entity.request.memo.MemoWriteRequestModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object MemoWriteMapper : BaseMapper() {
    fun MemoWriteRequestModel.toDto() = MemoWriteRequestDto(achievedDate = date, content = content)

    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<MemoWriteResponseDto>>): Flow<Result<Int>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.memoId ?: 0
            },
        )
    }
}
