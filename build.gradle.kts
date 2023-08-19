import io.gitlab.arturbosch.detekt.Detekt

// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.com.google.devtools.ksp) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.io.gitlab.arturbosch.detekt)

    id("dependency.checker")
}

allprojects {
    apply(plugin = rootProject.project.libs.plugins.io.gitlab.arturbosch.detekt.get().pluginId)

    dependencies {
        detektPlugins(rootProject.project.libs.io.gitlab.arturbosch.detekt.formatting) {
            exclude(
                group = "org.slf4j",
                module = "slf4j-nop",
            )
        }
    }

    detekt {
        config.setFrom(
            "${project.rootDir}/build-config/detekt-config.yml",
        )
        source.setFrom(
            "src/main/java",
            "src/test/java",
            "src/androidTest/java",
        )
        parallel = true
    }

    tasks.withType<Detekt>().configureEach {
        exclude("**/resources/**", "**/build/**")
    }
}

true // Needed to make the Suppress annotation work for the plugins block
