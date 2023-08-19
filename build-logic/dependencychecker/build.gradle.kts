plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.org.jetbrains.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("DependencyCheckerPlugin") {
            id = "dependency.checker"
            implementationClass = "com.stewemetal.dependencychecker.DependencyCheckerPlugin"
        }
    }
}
