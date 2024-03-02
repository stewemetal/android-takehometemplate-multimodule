@file:Suppress("UnstableApiUsage")

package com.stewemetal.convention.configuration

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = ANDROID_COMPILE_SDK_VERSION

        defaultConfig {
            minSdk = ANDROID_MIN_SDK_VERSION
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            testInstrumentationRunnerArguments["clearPackageData"] = "true"
            vectorDrawables {
                useSupportLibrary = true
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
//            isCoreLibraryDesugaringEnabled = true
        }

        testOptions {
            execution = "ANDROIDX_TEST_ORCHESTRATOR"
            animationsDisabled = true
            unitTests {
                isReturnDefaultValues = true
                isIncludeAndroidResources = true
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = "11"
            }
        }

        kotlinOptions {
            jvmTarget = JVM_TARGET
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

//    dependencies {
//        add("lintChecks", project(":lint"))
//    }
}

fun CommonExtension<*, *, *, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}
