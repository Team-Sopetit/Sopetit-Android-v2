import java.util.Properties

plugins {
    id("sopetit.android.feature")
    id("sopetit.android.compose")
}

val properties = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}

android {
    namespace = "com.sopetit.ui"

    defaultConfig {
        val feedbackUrl = properties.getProperty("FEEDBACK_FORM")
        buildConfigField("String", "FEEDBACK_FORM", "\"$feedbackUrl\"")
    }
}

dependencies {
    implementation(projects.core)
    implementation(projects.core.designSystem)
    implementation(projects.domain)

    // ThreeTen
    implementation(libs.threeten)
}