plugins {
    id("sopetit.android.feature")
    id("sopetit.android.compose")
}

android {
    namespace = "com.sopetit.navigation"
}

dependencies {
    implementation(libs.gson)
    implementation(projects.core)
    implementation(projects.core.ui)
    implementation(projects.domain)

    implementation(projects.feature.splash)
    implementation(projects.feature.login)
    implementation(projects.feature.onboarding)
    implementation(projects.feature.home)
    implementation(projects.feature.achieve)
    implementation(projects.feature.progress)
    implementation(projects.feature.addroutine)
    implementation(projects.feature.customroutine)
    implementation(projects.feature.setting)
}