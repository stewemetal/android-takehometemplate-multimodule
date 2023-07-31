plugins {
    id("com.stewemetal.takehometemplate.android.base.library")
    id("com.stewemetal.takehometemplate.android.library.compose")
    id("com.stewemetal.takehometemplate.android.library.koin.annotations")
}

android {
    namespace = "com.stewemetal.takehometemplate.design"
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.androidx.compose.material3)
}
