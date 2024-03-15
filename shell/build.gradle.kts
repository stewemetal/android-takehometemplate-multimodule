plugins {
    alias(libs.plugins.project.library.base)
    alias(libs.plugins.project.library.compose)
    alias(libs.plugins.project.library.koin.annotation)
    alias(libs.plugins.com.google.devtools.ksp)
}

android {
    namespace = "com.stewemetal.takehometemplate.shell"

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.androidx.appcompat)

    api(libs.io.insert.koin.android)

    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.moshi)
    implementation(libs.com.squareup.okhttp3.okhttp)
    implementation(libs.com.squareup.okhttp3.logging.interceptor)
    implementation(libs.androidx.material)
    ksp(libs.com.squareup.moshi.kotlin.codegen)

    implementation(libs.bundles.room)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)

    implementation(libs.com.jakewharton.timber)

    testImplementation(libs.androidx.room.testing)

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}
