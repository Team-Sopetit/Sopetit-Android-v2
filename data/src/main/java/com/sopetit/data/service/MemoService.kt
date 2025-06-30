package com.sopetit.data.service

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.base.EndPoints
import com.sopetit.data.entity.request.memo.MemoWriteRequestDto
import com.sopetit.data.entity.response.memo.MemoWriteResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MemoService {

    @POST(EndPoints.Memo.MEMOWRITE)
    suspend fun writeMemo(
        @Body body: MemoWriteRequestDto
    ): Response<BaseResponse<MemoWriteResponseDto>>
}