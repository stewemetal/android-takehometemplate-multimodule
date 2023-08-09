plugins {
    id("com.stewemetal.takehometemplate.android.application")
    id("com.stewemetal.takehometemplate.android.application.compose")
    id("com.stewemetal.takehometemplate.android.application.koin.annotations")
}

android {
    namespace = "com.stewemetal.takehometemplate"
    compileSdk = Integer.parseInt(libs.versions.android.compile.sdk.get())

    defaultConfig {
        applicationId = "com.stewemetal.takehometemplate"
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        resValue("string", "app_name", "TakeHomeTemplate")

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":shell"))

    implementation(project(":feature:login:contract"))
    implementation(project(":feature:login:impl"))
    implementation(project(":feature:home:contract"))
    implementation(project(":feature:home:impl"))
    implementation(project(":feature:item-details:contract"))
    implementation(project(":feature:item-details:impl"))

    implementation(project(":library:design"))

    implementation(libs.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.io.insert.koin.androidx.compose)

    implementation(libs.com.jakewharton.timber)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
