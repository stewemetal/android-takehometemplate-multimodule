@file:Suppress("UnstableApiUsage")

import com.android.build.api.dsl.LibraryExtension
import com.stewemetal.convention.configuration.Junit
import com.stewemetal.convention.configuration.KotestBundle
import com.stewemetal.convention.configuration.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidBaseLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                testOptions {
                    unitTests.all {
                        it.useJUnitPlatform()
                    }
                }
            }

            dependencies {
                add("testImplementation", KotestBundle)
                add("testImplementation", kotlin("test"))
                add("testImplementation", Junit)
            }
        }
    }
}
