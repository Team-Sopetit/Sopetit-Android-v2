plugins {
    id("sopetit.android.feature")
    id("sopetit.android.compose")
}

android {
    namespace = "com.sopetit.ui"
}

dependencies {
    implementation(projects.core)
}