package com.sopetit.data.dataSource

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.request.customroutine.CustomRoutineRequestDto
import com.sopetit.data.entity.response.createroutine.CustomRoutineResponseDto
import retrofit2.Response

interface CustomRoutineDataSource {
    suspend fun postCreateCustomRoutine(request: CustomRoutineRequestDto): Response<BaseResponse<CustomRoutineResponseDto>>
    suspend fun putModifyCustomRoutine(request: Int, body: CustomRoutineRequestDto): Response<BaseResponse<CustomRoutineResponseDto>>
    suspend fun deleteCustomRoutine(request: Int): Response<BaseResponse<Unit>>
}