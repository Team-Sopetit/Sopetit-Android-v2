package com.sopetit.data.dataSource.dataSourceImpl

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.dataSource.CustomRoutineDataSource
import com.sopetit.data.entity.request.customroutine.CreateCustomRoutineRequestDto
import com.sopetit.data.entity.response.createroutine.CreateCustomRoutineResponseDto
import com.sopetit.data.service.CustomRoutineService
import retrofit2.Response
import javax.inject.Inject

class CustomRoutineDataSourceImpl @Inject constructor(
    private val customRoutineService: CustomRoutineService
): CustomRoutineDataSource {

    override suspend fun postCreateCustomRoutine(request: CreateCustomRoutineRequestDto): Response<BaseResponse<CreateCustomRoutineResponseDto>> =
        customRoutineService.createRoutine(request)
}