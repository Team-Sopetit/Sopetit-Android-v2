package com.sopetit.data.dataSource.dataSourceImpl

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.dataSource.MemoDataSource
import com.sopetit.data.entity.request.memo.MemoWriteRequestDto
import com.sopetit.data.entity.response.memo.MemoWriteResponseDto
import com.sopetit.data.service.MemoService
import retrofit2.Response
import javax.inject.Inject

class MemoDataSourceImpl @Inject constructor(
    private val memoService: MemoService,
) : MemoDataSource {

    override suspend fun postWriteMemo(request: MemoWriteRequestDto): Response<BaseResponse<MemoWriteResponseDto>> =
        memoService.writeMemo(request)

    override suspend fun deleteMemo(request: Int): Response<BaseResponse<Unit>> =
        memoService.deleteMemo(request)
}