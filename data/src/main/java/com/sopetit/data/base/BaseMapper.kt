package com.sopetit.data.base

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class BaseMapper {

    fun <DTO, MODEL> baseMapper(
        apiCall: suspend () -> Response<BaseResponse<DTO>>,
        responseToModel: (DTO?) -> MODEL
    ): Flow<Result<MODEL>> = flow {
        val response = apiCall()

        when (response.isSuccessful) {
            true -> {
                val apiResponse = response.body() as BaseResponse
                val data = responseToModel(apiResponse.data)

                emit(Result.success(data))
            }
            false -> {
                val errorBody = response.errorBody()?.string() ?: ""
                val errorMessage = fromGson<DTO>(errorBody).message

                emit(Result.failure(Exception(errorMessage)))
            }
        }
    }

    private fun <T> fromGson(json: String?): BaseResponse<T> {
        return Gson().fromJson(json, object : TypeToken<BaseResponse<T>>() {}.type) ?: BaseResponse()
    }
}