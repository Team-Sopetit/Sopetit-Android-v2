plugins {
    `kotlin-dsl`
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(libs.agp)
    implementation(libs.kotlin.gradleplugin)
    compileOnly(libs.compose.compiler.extension)
}

gradlePlugin {
    plugins {
        register("android-application") {
            id = "sopetit.android.application"
            implementationClass = "com.sopetit.plugin.AndroidApplicationPlugin"
        }
        register("android-compose") {
            id = "sopetit.android.compose"
            implementationClass = "com.sopetit.plugin.AndroidComposePlugin"
        }
        register("android-feature") {
            id = "sopetit.android.feature"
            implementationClass = "com.sopetit.plugin.AndroidFeaturePlugin"
        }
        register("android-hilt") {
            id = "sopetit.android.hilt"
            implementationClass = "com.sopetit.plugin.AndroidHiltPlugin"
        }
        register("android-kotlin") {
            id = "sopetit.android.kotlin"
            implementationClass = "com.sopetit.plugin.AndroidKotlinPlugin"
        }
        register("kotlin-jvm") {
            id = "sopetit.kotlin.jvm"
            implementationClass = "com.sopetit.plugin.KotlinJvmPlugin"
        }
        register("kotlin-serialization") {
            id = "sopetit.kotlin.serialization"
            implementationClass = "com.sopetit.plugin.KotlinSerializationPlugin"
        }
        register("retrofit") {
            id = "sopetit.retrofit"
            implementationClass = "com.sopetit.plugin.RetrofitPlugin"
        }
    }
}