@file:Suppress("UnstableApiUsage")

package com.stewemetal.convention.configuration

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Configure Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = AndroidCompileSdkVersion

        defaultConfig {
            minSdk = AndroidMinSdkVersion
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            testInstrumentationRunnerArguments["clearPackageData"] = "true"
            vectorDrawables {
                useSupportLibrary = true
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        testOptions {
            execution = "ANDROIDX_TEST_ORCHESTRATOR"
            animationsDisabled = true
            unitTests {
                isReturnDefaultValues = true
                isIncludeAndroidResources = true
            }
        }

        tasks.withType<KotlinCompile>().configureEach {
            compilerOptions {
                jvmTarget.set(JvmTarget.fromTarget(JvmTargetVersion))
            }
        }

        packaging {
            resources {
                excludes.apply {
                    add("META-INF/*")
                    add("META-INF/DEPENDENCIES")
                    add("org/apache/http/version.properties")
                    add("META-INF/*.kotlin_module")
                    add("META-INF/*")
                }
                pickFirsts.add("**/lib/**")
            }
        }

        useLibrary("android.test.base")
    }

    kotlinExtension.sourceSets.configureEach {
        languageSettings.optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
    }
}
