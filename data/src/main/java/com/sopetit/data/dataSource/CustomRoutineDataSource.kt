package com.sopetit.data.dataSource

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.request.customroutine.CreateCustomRoutineRequestDto
import com.sopetit.data.entity.response.createroutine.CreateCustomRoutineResponseDto
import retrofit2.Response

interface CustomRoutineDataSource {
    suspend fun postCreateCustomRoutine(request: CreateCustomRoutineRequestDto): Response<BaseResponse<CreateCustomRoutineResponseDto>>
}