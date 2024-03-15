plugins {
    `kotlin-dsl`
}

group = "hu.stewemetal.takehometemplate.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile::class.java).configureEach {
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    compileOnly(libs.com.android.tools.build.gradle)
    compileOnly(libs.org.jetbrains.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "com.stewemetal.takehometemplate.android.application"
            implementationClass = "AndroidApplicationPlugin"
        }
        register("androidFeature") {
            id = "com.stewemetal.takehometemplate.android.feature"
            implementationClass = "AndroidFeaturePlugin"
        }
        register("androidApplicationCompose") {
            id = "com.stewemetal.takehometemplate.android.application.compose"
            implementationClass = "AndroidApplicationComposePlugin"
        }
        register("androidBaseLibrary") {
            id = "com.stewemetal.takehometemplate.android.base.library"
            implementationClass = "AndroidBaseLibraryPlugin"
        }
        register("androidLibrary") {
            id = "com.stewemetal.takehometemplate.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }
        register("androidLibraryCompose") {
            id = "com.stewemetal.takehometemplate.android.library.compose"
            implementationClass = "AndroidLibraryComposePlugin"
        }
        register("androidLibraryKoinAnnotation") {
            id = "com.stewemetal.takehometemplate.android.library.koin.annotations"
            implementationClass = "AndroidLibraryKoinAnnotationPlugin"
        }
        register("androidApplicationKoinAnnotation") {
            id = "com.stewemetal.takehometemplate.android.application.koin.annotations"
            implementationClass = "AndroidApplicationKoinAnnotationPlugin"
        }
    }
}
