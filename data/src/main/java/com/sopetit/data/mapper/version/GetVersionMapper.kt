package com.sopetit.data.mapper.version

import com.sopetit.data.base.BaseMapper
import com.sopetit.data.base.BaseResponse
import com.sopetit.data.entity.response.version.VersionResponseDto
import com.sopetit.domain.entity.response.version.VersionAppModel
import com.sopetit.domain.entity.response.version.VersionModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object GetVersionMapper: BaseMapper() {

    fun responseToModel(apiCall: suspend () -> Response<BaseResponse<VersionResponseDto>>): Flow<Result<VersionModel>> {
        return baseMapper(
            apiCall = { apiCall() },
            responseToModel = { response ->
                response?.let { data ->
                    VersionModel(
                        iosVersion = VersionAppModel(data.iosVersion.appVersion, data.iosVersion.forceUpdateVersion),
                        androidVersion = VersionAppModel(data.androidVersion.appVersion, data.androidVersion.forceUpdateVersion),
                        notificationTitle = data.notificationTitle,
                        notificationContent = data.notificationContent,
                        properties = data.properties
                    )
                } ?: VersionModel()
            }
        )
    }
}