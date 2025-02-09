plugins {
    id("sopetit.android.feature")
    id("sopetit.android.compose")
}

android {
    namespace = "com.sopetit.onboarding"
}

dependencies {
    implementation(projects.domain)
    implementation(projects.core)
    implementation(projects.core.ui)
    implementation(projects.core.designSystem)
}