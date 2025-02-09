plugins {
    id("sopetit.android.feature")
    id("sopetit.android.compose")
}

android {
    namespace = "com.sopetit.navigation"
}

dependencies {
    implementation(projects.core)
    implementation(projects.domain)

    implementation(projects.feature.onboarding)
}