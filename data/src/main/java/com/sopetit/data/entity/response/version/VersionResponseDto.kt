package com.sopetit.data.entity.response.version

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VersionResponseDto(
    @SerialName("iosVersion")
    val iosVersion: AppVersion,
    @SerialName("androidVersion")
    val androidVersion: AppVersion,
    @SerialName("notificationTitle")
    val notificationTitle: String = "",
    @SerialName("notificationContent")
    val notificationContent: String = "",
    @SerialName("properties")
    val properties: Map<String, String> = emptyMap(),
) {
    @Serializable
    data class AppVersion(
        @SerialName("appVersion")
        val appVersion: String = "",
        @SerialName("forceUpdateVersion")
        val forceUpdateVersion: String = "",
    )
}
