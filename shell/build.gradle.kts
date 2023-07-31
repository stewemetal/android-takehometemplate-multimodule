plugins {
    id("com.stewemetal.takehometemplate.android.base.library")
    id("com.stewemetal.takehometemplate.android.library.compose")
    id("com.stewemetal.takehometemplate.android.library.koin.annotations")
    alias(libs.plugins.com.google.devtools.ksp)
}

android {
    namespace = "com.stewemetal.shell"

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
    implementation(libs.androidx.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}
