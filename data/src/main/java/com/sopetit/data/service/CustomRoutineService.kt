package com.sopetit.data.service

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.base.EndPoints
import com.sopetit.data.entity.request.customroutine.CustomRoutineRequestDto
import com.sopetit.data.entity.response.createroutine.CustomRoutineResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CustomRoutineService {

    @POST(EndPoints.CustomRoutine.CREATE)
    suspend fun createRoutine(
        @Body body: CustomRoutineRequestDto
    ): Response<BaseResponse<CustomRoutineResponseDto>>

    @PUT(EndPoints.CustomRoutine.MODIFY)
    suspend fun modifyRoutine(
        @Path("customRoutineId") customRoutineId: Int,
        @Body body: CustomRoutineRequestDto
    ): Response<BaseResponse<CustomRoutineResponseDto>>

    @DELETE(EndPoints.CustomRoutine.MODIFY)
    suspend fun deleteRoutine(
        @Path("customRoutineId") customRoutineId: Int
    ): Response<BaseResponse<Unit>>
}