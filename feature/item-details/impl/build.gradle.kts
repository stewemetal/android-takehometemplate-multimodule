plugins {
    alias(libs.plugins.project.feature)
    alias(libs.plugins.project.library.koin.annotation)
    alias(libs.plugins.com.google.devtools.ksp)
}

android {
    namespace = "com.stewemetal.takehometemplate.item.details"
}

dependencies {
    implementation(project(":feature:item-details:contract"))

    implementation(project(":library:design"))

    implementation(libs.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.compose.material3)
    implementation(libs.io.insert.koin.androidx.compose)
}
