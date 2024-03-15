plugins {
    alias(libs.plugins.project.application)
    alias(libs.plugins.project.application.compose)
    alias(libs.plugins.project.application.koin.annotation)
}

android {
    namespace = "com.stewemetal.takehometemplate"
    compileSdk = Integer.parseInt(libs.versions.android.compile.sdk.get())

    defaultConfig {
        applicationId = "com.stewemetal.takehometemplate"
        versionCode = 1
        versionName = "1.0"
        resValue("string", "app_name", "TakeHomeTemplate")
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
            signingConfig = signingConfigs.getByName("debug")
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
    implementation(project(":feature:item-list:contract"))
    implementation(project(":feature:item-list:impl"))
    implementation(project(":feature:item-details:contract"))
    implementation(project(":feature:item-details:impl"))

    implementation(project(":library:design"))

    implementation(libs.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.io.insert.koin.androidx.compose)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
