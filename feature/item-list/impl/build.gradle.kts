plugins {
    id("com.stewemetal.takehometemplate.android.feature")
    id("com.stewemetal.takehometemplate.android.library.koin.annotations")
    alias(libs.plugins.com.google.devtools.ksp)
}

android {
    namespace = "com.stewemetal.takehometemplate.itemlist"

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
    implementation(project(":feature:item-list:contract"))

    implementation(project(":library:design"))

    implementation(libs.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.compose.material3)
    implementation(libs.io.insert.koin.androidx.compose)

    implementation(libs.com.jakewharton.timber)
}
