package com.stewemetal.convention.configuration

import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependencyBundle
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType

// https://github.com/gradle/gradle/issues/19813
internal fun Project.version(key: String): String = extensions
    .getByType<VersionCatalogsExtension>()
    .named("libs")
    .findVersion(key)
    .get()
    .requiredVersion

internal fun Project.bundle(key: String): Provider<ExternalModuleDependencyBundle> = extensions
    .getByType<VersionCatalogsExtension>()
    .named("libs")
    .findBundle(key)
    .get()

internal fun Project.library(key: String): Provider<MinimalExternalModuleDependency> = extensions
    .getByType<VersionCatalogsExtension>()
    .named("libs")
    .findLibrary(key)
    .get()

internal fun Project.versionInt(key: String) = version(key).toInt()

internal val Project.AndroidCompileSdkVersion get() = versionInt("android.compile.sdk")
internal val Project.AndroidMinSdkVersion get() = versionInt("android.min.sdk")
internal val Project.AndroidTargetSdkVersion get() = versionInt("android.target.sdk")
internal val Project.JvmTargetVersion get() = version("jvm.target")
internal val Project.ComposeCompilerVersion get() = version("androidx.compose.compiler")
internal val Project.ComposeBundle get() = bundle("compose")
internal val Project.ComposeBom get() = library("androidx.compose.bom")
internal val Project.ComposeTestingBundle get() = bundle("compose.testing")
internal val Project.ComposeUiTooling get() = library("androidx.compose.ui.tooling")
internal val Project.ComposeTestingManifest get() = library("androidx.compose.ui.test.manifest")
internal val Project.KotestBundle get() = bundle("kotest")
internal val Project.Junit get() = library("junit")
internal val Project.KoinAnnotations get() = library("io.insert.koin.annotations")
internal val Project.KoinKspCompiler get() = library("io.insert.koin.ksp.compiler")
internal val Project.KoinTest get() = library("io.insert.koin.test")
internal val Project.TestOrchestrator get() = library("androidx.test.orchestrator")
internal val Project.Timber get() = library("com.jakewharton.timber")
