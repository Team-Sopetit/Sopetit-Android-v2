// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlinx.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.google.serive) apply false
    alias(libs.plugins.ktlint) apply  false
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    extensions.configure<org.jlleitschuh.gradle.ktlint.KtlintExtension>("ktlint") {
        version.set("1.2.1")
        android.set(true)
        ignoreFailures.set(false)

        filter {
            exclude("**/src/test/**")
            exclude("**/src/androidTest/**")
        }
    }

    listOf(
        "ktlintAndroidTestSourceSetCheck",
        "ktlintAndroidTestSourceSetFormat",
        "ktlintTestSourceSetCheck",
        "ktlintTestSourceSetFormat",
    ).forEach { n ->
        tasks.matching { it.name == n }.configureEach { enabled = false }
    }
}