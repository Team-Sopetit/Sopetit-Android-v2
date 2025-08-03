plugins {
    id("sopetit.android.feature")
    id("sopetit.android.compose")
}

android {
    namespace = "com.sopetit.design_system"
}

dependencies {
    implementation(projects.core)
}