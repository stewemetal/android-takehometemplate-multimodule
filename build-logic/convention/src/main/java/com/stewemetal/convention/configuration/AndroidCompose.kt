package com.stewemetal.convention.configuration

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Configure Compose-specific options
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {
        composeOptions.kotlinCompilerExtensionVersion = project.ComposeCompilerVersion
        buildFeatures.compose = true

        with(project) {
            dependencies {
                add("implementation", platform(ComposeBom))
                add("implementation", ComposeBundle)

                add("debugImplementation", ComposeUiTooling)

                add("androidTestImplementation", ComposeTestingBundle)
                add("androidTestImplementation", ComposeTestingManifest)
            }
        }
    }
}
