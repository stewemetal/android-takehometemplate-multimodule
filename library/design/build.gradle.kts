plugins {
    alias(libs.plugins.project.library.base)
    alias(libs.plugins.project.library.compose)
}

android {
    namespace = "com.stewemetal.takehometemplate.design"
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.androidx.compose.material3)
}
