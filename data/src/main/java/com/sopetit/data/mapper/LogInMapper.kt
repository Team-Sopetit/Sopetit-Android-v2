package com.sopetit.data.mapper

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.request.LogInRequestDto
import com.sopetit.data.entity.response.LogInResponseDto
import com.sopetit.domain.entity.request.LogInRequestModel
import com.sopetit.domain.entity.response.LogInResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.Reader

object LogInMapper {
    fun LogInRequestModel.toDto() = LogInRequestDto(
        socialType = socialType
    )

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
                val errorBody = response.errorBody()?.string() ?: ""
                val errorMessage = try {
                    val gson = Gson()
                    val type = object : TypeToken<BaseResponse<Any>>() {}.type
                    val errorResponse: BaseResponse<Any> = gson.fromJson(errorBody, type)
                    errorResponse.message
                } catch (e: Exception) {
                    errorBody
                }

                emit(Result.failure(Exception(errorMessage)))

//            emit(Result.failure(response.errorBody()))
            }
        }
    }
}