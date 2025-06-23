plugins {
    id("sopetit.android.feature")
    id("sopetit.android.compose")
}

android {
    namespace = "com.sopetit.ui"
}

dependencies {
    implementation(projects.core)
    implementation(projects.core.designSystem)
    implementation(projects.domain)

    // ThreeTen
    implementation(libs.threeten)
}