plugins {
    id("com.stewemetal.takehometemplate.android.base.library")
    id("com.stewemetal.takehometemplate.android.library.compose")
    id("com.stewemetal.takehometemplate.android.library.koin.annotations")
    alias(libs.plugins.com.google.devtools.ksp)
}

android {
    namespace = "com.stewemetal.takehometemplate.shell"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildConfigField("String", "VERSION_NAME", "\"1.0\"")
    }

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
    ksp(libs.com.squareup.moshi.kotlin.codegen)

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}
