plugins {
    id("sopetit.android.feature")
    id("sopetit.retrofit")
    id("sopetit.android.hilt")
}

android {
    namespace = "com.sopetit.data"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(projects.domain)
    implementation(projects.core)

    implementation(libs.androidx.datastore)
    implementation(libs.gson)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.okhttp.urlconnection)
}