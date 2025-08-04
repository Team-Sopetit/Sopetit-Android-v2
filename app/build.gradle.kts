import java.util.Properties

plugins {
    id("sopetit.android.application")
    id("sopetit.android.hilt")
    id("sopetit.android.kotlin")
    id("sopetit.retrofit")
    id("com.google.gms.google-services")
}

val properties = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}

android {
    namespace = "com.sopetit.softie"

    defaultConfig {
        val baseUrl = properties.getProperty("BASE_URL")
        buildConfigField("String", "BASE_URL", "\"$baseUrl\"")

        val kakaoAppKey = properties.getProperty("KAKAO_APP_KEY")
        buildConfigField("String", "KAKAO_APP_KEY", "\"${properties.getProperty("KAKAO_APP_KEY")}\"")

        manifestPlaceholders["KAKAO_APP_KEY"] = kakaoAppKey
        manifestPlaceholders["KAKAO_HOST_SCHEME"] = "kakao$kakaoAppKey"
        versionCode = project.properties["version_code"]?.toString()?.toInt() ?: 1
        versionName = project.properties["version"]?.toString() ?: "1.0.0"
    }
}

dependencies {
    implementation(projects.feature)
    implementation(projects.domain)
    implementation(projects.core)
    implementation(projects.core.firebase)
    implementation(projects.data)

    implementation(libs.gson)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.okhttp.urlconnection)

    implementation(libs.kakao.auth)

    // ThreeTen
    implementation(libs.threeten)

    // FCM
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.messaging)
}