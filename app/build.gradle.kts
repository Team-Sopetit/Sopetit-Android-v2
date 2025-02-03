plugins {
    id("sopetit.android.application")
    id("sopetit.android.hilt")
    id("sopetit.android.kotlin")
    id("sopetit.retrofit")
}

android {
    namespace = "com.sopetit.softie"

    defaultConfig {

    }
}

dependencies {
    implementation(projects.feature)
    implementation(projects.domain)
    implementation(projects.core)
    implementation(projects.data)
}