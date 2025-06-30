package com.sopetit.data.dataSource

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.request.memo.MemoWriteRequestDto
import com.sopetit.data.entity.response.memo.MemoWriteResponseDto
import retrofit2.Response

interface MemoDataSource {
    suspend fun postWriteMemo(request: MemoWriteRequestDto): Response<BaseResponse<MemoWriteResponseDto>>
    suspend fun deleteMemo(request: Int): Response<BaseResponse<Unit>>
}