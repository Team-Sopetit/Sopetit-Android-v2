package com.sopetit.domain.entity.response.version

data class VersionModel(
    val iosVersion: VersionAppModel = VersionAppModel(),
    val androidVersion: VersionAppModel = VersionAppModel(),
    val notificationTitle: String = "",
    val notificationContent: String = "",
    val properties: Map<String, String> = emptyMap(),
)
