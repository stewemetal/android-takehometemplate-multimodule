plugins {
    `kotlin-dsl`
}

group = "hu.stewemetal.calibrex.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile::class.java).configureEach {
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    compileOnly(libs.com.android.tools.build.gradle)
    compileOnly(libs.org.jetbrains.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "com.stewemetal.calibrex.android.application"
            implementationClass = "AndroidApplicationPlugin"
        }
        register("androidFeature") {
            id = "com.stewemetal.calibrex.android.feature"
            implementationClass = "AndroidFeaturePlugin"
        }
        register("androidApplicationCompose") {
            id = "com.stewemetal.calibrex.android.application.compose"
            implementationClass = "AndroidApplicationComposePlugin"
        }
        register("androidBaseLibrary") {
            id = "com.stewemetal.calibrex.android.base.library"
            implementationClass = "AndroidBaseLibraryPlugin"
        }
        register("androidLibrary") {
            id = "com.stewemetal.calibrex.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }
        register("androidLibraryCompose") {
            id = "com.stewemetal.calibrex.android.library.compose"
            implementationClass = "AndroidLibraryComposePlugin"
        }
        register("androidLibraryKoinAnnotation") {
            id = "com.stewemetal.calibrex.android.library.koin.annotations"
            implementationClass = "AndroidLibraryKoinAnnotationPlugin"
        }
        register("androidApplicationKoinAnnotation") {
            id = "com.stewemetal.calibrex.android.application.koin.annotations"
            implementationClass = "AndroidApplicationKoinAnnotationPlugin"
        }
    }
}
