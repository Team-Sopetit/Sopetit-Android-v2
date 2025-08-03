package com.sopetit.data.dataSource.dataSourceImpl

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.dataSource.CustomRoutineDataSource
import com.sopetit.data.entity.request.customroutine.CustomRoutineRequestDto
import com.sopetit.data.entity.response.createroutine.CustomRoutineResponseDto
import com.sopetit.data.service.CustomRoutineService
import retrofit2.Response
import javax.inject.Inject

class CustomRoutineDataSourceImpl @Inject constructor(
    private val customRoutineService: CustomRoutineService
): CustomRoutineDataSource {

    override suspend fun postCreateCustomRoutine(request: CustomRoutineRequestDto): Response<BaseResponse<CustomRoutineResponseDto>> =
        customRoutineService.createRoutine(request)

    override suspend fun putModifyCustomRoutine(
        request: Int,
        body: CustomRoutineRequestDto
    ): Response<BaseResponse<CustomRoutineResponseDto>> =
        customRoutineService.modifyRoutine(request, body)

    override suspend fun deleteCustomRoutine(request: Int): Response<BaseResponse<Unit>> =
        customRoutineService.deleteRoutine(request)
}