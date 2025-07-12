package com.sopetit.data.service

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.base.EndPoints
import com.sopetit.data.entity.request.customroutine.CreateCustomRoutineRequestDto
import com.sopetit.data.entity.response.createroutine.CreateCustomRoutineResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CustomRoutineService {

    @POST(EndPoints.CustomRoutine.CREATE)
    suspend fun createRoutine(
        @Body body: CreateCustomRoutineRequestDto
    ): Response<BaseResponse<CreateCustomRoutineResponseDto>>
}