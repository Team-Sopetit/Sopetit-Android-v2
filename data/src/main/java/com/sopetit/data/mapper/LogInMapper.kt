package com.sopetit.data.mapper

import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.request.LogInRequestDto
import com.sopetit.data.entity.response.LogInResponseDto
import com.sopetit.domain.entity.request.LogInRequestModel
import com.sopetit.domain.entity.response.LogInResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

fun LogInRequestModel.toDto() = LogInRequestDto(
    socialType = socialType
)

//fun responseToModel(response: LogInResponseDto?): Flow<Result<LogInResponseModel>> = flow {
//    val data = response?.let {
//        LogInResponseModel(
//            accessToken = it.accessToken,
//            refreshToken = it.refreshToken,
//            isMemberDollExist = it.isMemberDollExist
//        )
//    } ?: LogInResponseModel()
//
//    emit(Result.success(data))
//}

fun responseToModel(apiCall: suspend () -> Response<BaseResponse<LogInResponseDto>>): Flow<Result<LogInResponseModel>> = flow {
    val response = apiCall()

    when (response.isSuccessful) {
        true -> {
            val apiResponse = response.body() as BaseResponse
            val data = apiResponse.data?.let { data ->
                LogInResponseModel(
                    accessToken = data.accessToken,
                    refreshToken = data.refreshToken,
                    isMemberDollExist = data.isMemberDollExist
                )
            } ?: LogInResponseModel()

            emit(Result.success(data))
        }
        false -> {

//            emit(Result.failure(response.errorBody()))
        }
    }
}
