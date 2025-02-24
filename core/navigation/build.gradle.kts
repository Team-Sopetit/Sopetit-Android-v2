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

    implementation(projects.feature.splash)
    implementation(projects.feature.login)
    implementation(projects.feature.onboarding)
    implementation(projects.feature.home)
    implementation(projects.feature.achieve)
    implementation(projects.feature.progress)
}