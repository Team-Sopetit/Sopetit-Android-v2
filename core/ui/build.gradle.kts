import java.util.Properties

plugins {
    id("sopetit.android.feature")
    id("sopetit.android.compose")
}

val properties =
    Properties().apply {
        load(rootProject.file("local.properties").inputStream())
    }

android {
    namespace = "com.sopetit.ui"

    defaultConfig {
        val feedbackUrl = properties.getProperty("FEEDBACK_FORM")
        buildConfigField("String", "FEEDBACK_FORM", "\"$feedbackUrl\"")

        val servicePolicyUrl = properties.getProperty("SERVICE_POLICY")
        buildConfigField("String", "SERVICE_POLICY", "\"$servicePolicyUrl\"")

        val personalInfoPolicyUrl = properties.getProperty("PERSONAL_INFO_POLICY")
        buildConfigField("String", "PERSONAL_INFO_POLICY", "\"$personalInfoPolicyUrl\"")
    }
}

dependencies {
    implementation(projects.core)
    implementation(projects.core.designSystem)
    implementation(projects.domain)

    // ThreeTen
    implementation(libs.threeten)

    testImplementation(libs.junit)
}
