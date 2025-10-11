plugins {
    id("sopetit.android.feature")
    id("sopetit.android.compose")
    id("sopetit.android.hilt")
    id("sopetit.android.kotlin")
}

android {
    namespace = "com.sopetit.feature"
}

dependencies {
    implementation(projects.domain)
    implementation(projects.core)
    implementation(projects.core.ui)
    implementation(projects.core.designSystem)
    implementation(projects.core.navigation)
}