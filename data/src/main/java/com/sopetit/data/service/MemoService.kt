package com.sopetit.data.service

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.base.EndPoints
import com.sopetit.data.entity.request.memo.MemoWriteRequestDto
import com.sopetit.data.entity.response.memo.MemoWriteResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface MemoService {

    @POST(EndPoints.Memo.MEMOWRITE)
    suspend fun writeMemo(
        @Body body: MemoWriteRequestDto
    ): Response<BaseResponse<MemoWriteResponseDto>>

    @DELETE(EndPoints.Memo.DELETE)
    suspend fun deleteMemo(
        @Path("memoId") memoId: Int
    ): Response<BaseResponse<Unit>>
}