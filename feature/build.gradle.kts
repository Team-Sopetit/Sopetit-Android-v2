import java.util.Properties

plugins {
    id("sopetit.android.feature")
    id("sopetit.android.compose")
    id("sopetit.android.hilt")
    id("sopetit.android.kotlin")
}

val properties = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}

android {
    namespace = "com.sopetit.feature"

    defaultConfig {
        val feedbackUrl = properties.getProperty("FEEDBACK_FORM")
        buildConfigField("String", "FEEDBACK_FORM", "\"$feedbackUrl\"")
    }
}

dependencies {
    implementation(projects.domain)
    implementation(projects.core)
    implementation(projects.core.ui)
    implementation(projects.core.designSystem)
    implementation(projects.core.navigation)
}